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
package org.apache.fontbox.cff.encoding;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This is the superclass for all CFFFont encodings.
 * 
 * @author Villu Russmann
 * @version $Revision$
 */
public abstract class CFFEncoding
{

    private static HashMap<Integer,Integer> code2sid = new HashMap<Integer,Integer>();
    private static HashMap<Integer,Integer> sid2code = new HashMap<Integer,Integer>();

    /**
     * Determines if the encoding is font specific or not.
     * @return if the encoding is font specific
     */
    public boolean isFontSpecific()
    {
        return false;
    }

    /**
     * Returns the code corresponding to the given SID.
     * @param sid the given SID
     * @return the corresponding code
     */
    public int getCode(int sid)
    {
        if (sid2code.containsKey(sid)) 
        {
            return sid2code.get(sid);
        }
        return -1;
    }

    /**
     * Returns the SID corresponding to the given code.
     * @param code the given code
     * @return the corresponding SID
     */
    public int getSID(int code)
    {
        if (code2sid.containsKey(code)) 
        {
            return code2sid.get(code);
        }
        return -1;
    }

    /**
     * Adds a new code/SID combination to the encoding.
     * @param code the given code
     * @param sid the given SID
     */
    public void register(int code, int sid)
    {
        // TODO check if mapping already exists
        sid2code.put(sid,code);
        code2sid.put(code,sid);
    }

    /**
     * Returns an iterator for all codes of this encoding.
     * 
     * @return the iterator for all codes of this encoding
     */
    public Iterator<Integer> getCodes() 
    {
        return code2sid.keySet().iterator();
    }

    public Object getEntries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}