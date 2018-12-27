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
package org.opennars;

import java.io.Serializable;

public class LockedValueTypes {
    public static class Lock extends Object implements Serializable { }
    //Because AtomicInteger/Double ot supported by teavm
    public static class PortableInteger implements Serializable {
        public PortableInteger(){}
        final Lock lock = new Lock();
        int VAL = 0;
        public PortableInteger(final int VAL){synchronized(lock){this.VAL = VAL;}}
        public void set(final int VAL){synchronized(lock){this.VAL = VAL;}}
        public int get() {return this.VAL;}
        public float floatValue() {return (float)this.VAL;}
        public float doubleValue() {return (float)this.VAL;}
        public int intValue() {return this.VAL;}
        public int incrementAndGet(){int ret = 0; synchronized(lock){this.VAL++; ret=this.VAL;} return ret;}
    }
    public static class PortableDouble implements Serializable {
        final Lock lock = new Lock();
        public PortableDouble(){}
        double VAL = 0;
        public PortableDouble(final double VAL){synchronized(lock){this.VAL = VAL;}}
        public void set(final double VAL){synchronized(lock){this.VAL = VAL;}}
        public double get() {return this.VAL;}
        public float floatValue() {return (float)this.VAL;}
        public float doubleValue() {return (float)this.VAL;}
        public int intValue() {return (int)this.VAL;}
    }
}
