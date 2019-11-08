// ITimeAidlInterface.aidl
package id.ac.ui.cs.mobileprogramming.fariqodriandana.tutorial4;

// Declare any non-default types here with import statements

interface ITimeAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int getPid();

    String getTimeString(long timeMillis);
}
