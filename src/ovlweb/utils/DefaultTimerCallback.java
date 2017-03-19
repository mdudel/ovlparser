/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;


/**
 * Abstract Class implementation of ICallback
 * @author kennethkeith
 */
public class DefaultTimerCallback implements ICallback{

    public DefaultTimerCallback(){}
    
    /**
     * This is the Callback method called by the AbstractTimer Class (If you pass it in obviously)
     */
    @Override
    public void OnCallback() {
        System.out.println("OnCallback Called!");
    }
    
}
