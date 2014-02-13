/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.pdmodel.graphics.shading;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.ColorModel;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.common.PDRange;
import org.apache.pdfbox.util.Matrix;

/**
 * This represents the Paint of a type 4 (Gouraud triangle mesh) shading context.
 * 
 * @author Tilman Hausherr
 */
class Type4ShadingContext extends GouraudShadingContext
{
    private static final Log LOG = LogFactory.getLog(Type4ShadingContext.class);

    private PDShadingType4 shadingType;
    private int bitsPerFlag;
    

    /**
     * Constructor creates an instance to be used for fill operations.
     * 
     * @param shadingType4 the shading type to be used
     * @param colorModelValue the color model to be used
     * @param xform transformation for user to device space
     * @param ctm current transformation matrix
     * @param pageHeight height of the current page
     * 
     */
    public Type4ShadingContext(PDShadingType4 shadingType4, ColorModel colorModelValue,
            AffineTransform xform, Matrix ctm, int pageHeight) throws IOException
    {
        super(shadingType4, colorModelValue, xform, ctm, pageHeight);
        
        ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
        shadingType = shadingType4;

        LOG.debug("Type4ShadingContext");

        bitsPerColorComponent = shadingType.getBitsPerComponent();
        LOG.debug("bitsPerColorComponent: " + bitsPerColorComponent);
        bitsPerCoordinate = shadingType.getBitsPerCoordinate();
        LOG.debug(Math.pow(2, bitsPerCoordinate) - 1);
        long maxSrcCoord = (int) Math.pow(2, bitsPerCoordinate) - 1;
        long maxSrcColor = (int) Math.pow(2, bitsPerColorComponent) - 1;
        LOG.debug("maxSrcCoord: " + maxSrcCoord);
        LOG.debug("maxSrcColor: " + maxSrcColor);

        COSDictionary cosDictionary = shadingType.getCOSDictionary();
        COSStream cosStream = (COSStream) cosDictionary;

        //The Decode key specifies how
        //to decode coordinate and color component data into the ranges of values
        //appropriate for each. The ranges are specified as [xmin xmax ymin ymax c1,min,
        //c1,max,..., cn, min, cn,max].
        //
        // see p344
        COSArray decode = (COSArray) cosDictionary.getDictionaryObject(COSName.DECODE);
        LOG.debug("decode: " + decode);
        PDRange rangeX = shadingType4.getDecodeForParameter(0);
        PDRange rangeY = shadingType4.getDecodeForParameter(1);
        LOG.debug("rangeX: " + rangeX.getMin() + ", " + rangeX.getMax());
        LOG.debug("rangeY: " + rangeY.getMin() + ", " + rangeY.getMax());

        PDRange[] colRangeTab = new PDRange[numberOfColorComponents];
        for (int i = 0; i < numberOfColorComponents; ++i)
        {
            colRangeTab[i] = shadingType.getDecodeForParameter(2 + i);
        }

        LOG.debug("bitsPerCoordinate: " + bitsPerCoordinate);
        bitsPerFlag = shadingType.getBitsPerFlag();
        if (shadingType.getFunction() != null)
        {
            LOG.error("function based type 4 shading not implemented, please file issue with sample file");
        }
        LOG.debug("function: " + shadingType.getFunction()); //TODO implement function based shading
        LOG.debug("bitsPerFlag: " + bitsPerFlag); //TODO handle cases where bitperflag isn't 8
        LOG.debug("Stream size: " + cosStream.getInt(COSName.LENGTH));
        
        // get background values if available
        COSArray bg = shadingType.getBackground();
        if (bg != null)
        {
            background = bg.toFloatArray();
        }
        
        //TODO missing: BBox, AntiAlias (p. 305 in 1.7 spec)
        
        // p318:
        //  reading in sequence from higher-order to lower-order bit positions
        ImageInputStream mciis = new MemoryCacheImageInputStream(cosStream.getFilteredStream());
        while (true)
        {
            try
            {
                byte flag = (byte) (mciis.readBits(bitsPerFlag) & 3);
                LOG.debug("flag: " + flag);
                switch (flag)
                {
                    case 0:
                        Vertex v1 = readVertex(mciis, flag, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRangeTab);
                        Vertex v2 = readVertex(mciis, (byte) mciis.readBits(bitsPerFlag), maxSrcCoord, maxSrcColor, 
                                rangeX, rangeY, colRangeTab);
                        Vertex v3 = readVertex(mciis, (byte) mciis.readBits(bitsPerFlag), maxSrcCoord, maxSrcColor, 
                                rangeX, rangeY, colRangeTab);

                        // add them after they're read, so that they are never added if there is a premature EOF
                        vertexList.add(v1);
                        vertexList.add(v2);
                        vertexList.add(v3);
                        break;
                    case 1:
                    case 2:
                        vertexList.add(readVertex(mciis, flag, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRangeTab));
                        break;
                    default:
                        LOG.warn("bad flag: " + flag);
                        break;
                }
            }
            catch (EOFException ex)
            {
                LOG.debug("EOF");
                if (vertexList.size() < 3)
                {
                    LOG.warn("Incomplete mesh is ignored");
                    vertexList.clear();
                }
                else if (vertexList.size() > 1 && vertexList.get(0).flag != 0)
                {
                    LOG.warn("Mesh with incorrect start flag " + vertexList.get(0).flag + " is ignored");
                    vertexList.clear();
                }
                // check that there are 3 entries if there is a 0 flag
                int vi = 0;
                while (vi < vertexList.size())
                {
                    if (vertexList.get(vi).flag == 0)
                    {
                        if (vi + 2 >= vertexList.size())
                        {
                            LOG.warn("Mesh with incomplete triangle");
                            // remove rest
                            while (vertexList.size() >= vi + 1)
                            {
                                vertexList.remove(vi);
                            }
                            break;
                        }
                        vi += 3;
                    }
                    else
                    {
                        ++vi;
                    }
                }
                break;
            }
        }
        mciis.close();
        transformVertices(vertexList, ctm, xform, pageHeight);
        createTriangleList(vertexList);
        createArea();
    }

    /**
     * Create GouraudTriangle list from vertices, see p.316 of pdf spec 1.7.
     * 
     * @param vertexList list of vertices
     */
    private void createTriangleList(ArrayList<Vertex> vertexList)
    {
        Point2D a = null, b = null, c = null;
        float[] aColor = null, bColor = null, cColor = null;
        int vi = 0;
        while (vi < vertexList.size())
        {
            Vertex v = vertexList.get(vi);
            switch (v.flag)
            {
                case 0:
                    a = v.point;
                    aColor = v.color;
                    ++vi;

                    v = vertexList.get(vi);
                    b = v.point;
                    bColor = v.color;
                    ++vi;

                    v = vertexList.get(vi);
                    c = v.point;
                    cColor = v.color;
                    break;

                case 1:
                    a = b;
                    aColor = bColor;

                    b = c;
                    bColor = cColor;

                    v = vertexList.get(vi);
                    c = v.point;
                    cColor = v.color;
                    break;

                case 2:
                    b = c;
                    bColor = cColor;

                    v = vertexList.get(vi);
                    c = v.point;
                    cColor = v.color;
                    break;

                default:
                    break;
            }
            ++vi;
            triangleList.add(new GouraudTriangle(a, aColor, b, bColor, c, cColor));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose()
    {
        super.dispose();
        shadingType = null;
    }
}
