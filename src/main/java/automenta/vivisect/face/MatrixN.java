/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package automenta.vivisect.face;

/**
 *
 * @author me
 */


   
class MatrixN   
{   
   
    MatrixN(int i)   
    {   
        v = new VectorN[i];   
        for(int j = 0; j < i; j++)   
            v[j] = new VectorN(i);   
   
    }   
   
    VectorN get(int i)   
    {   
        return v[i];   
    }   
   
    double get(int i, int j)   
    {   
        return get(i).get(j);   
    }   
   
    void identity()   
    {   
        for(int i = 0; i < size(); i++)   
        {   
            for(int j = 0; j < size(); j++)   
                set(j, i, j != i ? 0 : 1);   
   
        }   
   
    }   
   
    void postMultiply(MatrixN matrixn)   
    {   
        MatrixN matrixn1 = new MatrixN(size());   
        for(int i = 0; i < size(); i++)   
        {   
            for(int j = 0; j < size(); j++)   
            {   
                double d = 0.0D;   
                for(int k = 0; k < size(); k++)   
                    d += get(j, k) * matrixn.get(k, i);   
   
                matrixn1.set(j, i, d);   
            }   
   
        }   
   
        set(matrixn1);   
    }   
   
    void preMultiply(MatrixN matrixn)   
    {   
        MatrixN matrixn1 = new MatrixN(size());   
        for(int i = 0; i < size(); i++)   
        {   
            for(int j = 0; j < size(); j++)   
            {   
                double d = 0.0D;   
                for(int k = 0; k < size(); k++)   
                    d += matrixn.get(j, k) * get(k, i);   
   
                matrixn1.set(j, i, d);   
            }   
   
        }   
   
        set(matrixn1);   
    }   
   
    void set(int i, int j, double d)   
    {   
        v[i].set(j, d);   
    }   
   
    void set(int i, VectorN vectorn)   
    {   
        v[i].set(vectorn);   
    }   
   
    void set(MatrixN matrixn)   
    {   
        for(int i = 0; i < size(); i++)   
            set(i, matrixn.get(i));   
   
    }   
   
    int size()   
    {   
        return v.length;   
    }   
   
    public String toString()   
    {   
        String s = "{";   
        for(int i = 0; i < size(); i++)   
            s = s + (i != 0 ? "," : "") + get(i);   
   
        return s + "}";   
    }   
   
    private VectorN v[];   
}  