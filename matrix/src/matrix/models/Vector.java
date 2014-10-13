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
package matrix.models;

import java.util.Arrays;

/**
 * Holds values of and basic information about a vector
 * @author Bao
 */
public class Vector {
    private double[] components;
    
    public Vector(double[] c) {
        components = c;
    }
    
    public int size() {
        return components.length;
    }
    
    public double get(int i) {
        return components[i-1];
    }

    @Override
    public String toString() {
        if (components != null)
            return Arrays.toString(components);
        return null;
    }
}
