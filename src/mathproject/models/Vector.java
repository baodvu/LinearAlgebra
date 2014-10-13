/*
 * Copyright 2014 Bao.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mathproject.models;

import java.util.Arrays;

/**
 * Holds values of and basic information about a vector
 * @author Bao
 */
public class Vector extends Matrix{
    
    public Vector(double... c) {
        super(c.length, 1);
        super.put(c);
    }
    
    public int size() {
        return super.getNumberOfRows();
    }
    
    public double get(int i) {
        return super.get(i, 1);
    }
    
    public double dotProduct(Vector v2) {
        return super.transpose().multiply(v2).get(1, 1);
    }
    
    @Override
    public boolean equals(Object obj) {
        Vector vec2 = (Vector) obj;
        if (size() != vec2.size()) return false;
        boolean equality = true;
        for (int i = 1; i <= size(); i++)
            if (get(i) != vec2.get(i)) {
                equality = false;
            }
        return equality;
    }
    
}
