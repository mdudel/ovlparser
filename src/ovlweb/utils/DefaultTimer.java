/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Default Timer
 * @author kennethkeith
 */
public class DefaultTimer {

    private final Timer timer = new Timer();
    private ICallback callback = null;
    private int delay = 0;
    private boolean debug = false;
    private TimerStates state = null;
    private int interval;
    TimeSettings setting;

    /**
     * Creates a Default Timer that only runs once. Once the delay has elapsed
     * it will call the OnTick method. Ensure you Implement/Override it!
     * @param delay Time in seconds
     * @param debug If you enable debug, you must Implement/Override the OnDebug method to handle the messages
     * @param setting Sets the precision of the time intervals 
     * 
     */
    public DefaultTimer(int delay, TimeSettings setting, boolean debug) {
        this.delay = delay;
        this.debug = debug;
        this.callback = null;
        this.state = TimerStates.STARTED;
        if (this.callback == null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay, interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 1000, interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }


            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
        if (this.callback != null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay,interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 1000,interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }

            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
    }

    /**
     * Creates a Default Timer that will repeat indefinitely based on the provided interval.
     * You can however call the Stop/Pause/Resume methods to control the execution of this Timer.
     * Once the delay has elapsed it will call the OnTick method. Ensure you Implement/Override it!
     * @param delay Time delay in seconds before the task starts
     * @param interval Time between repeat
     * @param debug If you enable debug, you must Implement/Override the OnDebug method to handle the messages
     * @param setting Sets the precision of the time intervals
     */
    public DefaultTimer(int delay, int interval, TimeSettings setting, boolean debug) {
        this.delay = delay;
        this.interval = interval;
        this.debug = debug;
        this.callback = null;
        this.state = TimerStates.STARTED;
        if (this.callback == null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay, interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 1000, interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }


            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
        if (this.callback != null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay,interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 1000,interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }

            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
    }

    public DefaultTimer(int delay, TimeSettings setting, ICallback icb, boolean autostart) {
        this.delay = delay;
        this.debug = false;
        this.callback = icb;
        this.state = TimerStates.STARTED;
        if (this.callback == null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay, interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 1000, interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }


            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
        if (this.callback != null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay,interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 1000,interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }

            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
    }

    /**
     * Creates a Default Timer that only runs once.
     * Once the delay has elapsed it will call the ICallback Object's OnCallback method. 
     * @param delay Time in seconds
     * @param debug If you enable debug, you must Implement/Override the OnDebug method to handle the messages
     * @param setting Sets the precision of the time intervals 
     * @param icb Object that has implemented ICallback.
     */
    public DefaultTimer(int delay, TimeSettings setting, ICallback icb, boolean debug, boolean autostart) {
        this.delay = delay;
        this.debug = debug;
        this.callback = icb;
        this.state = TimerStates.STARTED;
        if (this.callback == null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay, interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 1000, interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }


            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
        if (this.callback != null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay,interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 1000,interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }

            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
    }

    /**
     * Creates a Default Timer that will repeat indefinitely based on the provided interval.
     * You can however call the Stop/Pause/Resume methods to control the execution of this Timer.
     * Once the delay has elapsed it will call the ICallback Object's OnCallback method. 
     * @param delay Time in seconds before the task starts
     * @param interval Time 
     * @param debug If you enable debug, you must Implement/Override the OnDebug method to handle the messages
     * @param setting Sets the precision of the time intervals
     * @param icb Object that has implemented ICallback.
     */
    public DefaultTimer(int delay, int interval, TimeSettings setting, ICallback icb, boolean debug, boolean autostart) {
        this.delay = delay;
        this.interval = interval;
        this.debug = debug;
        this.callback = icb;
        this.state = TimerStates.STARTED;
        if (this.callback == null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay, interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 1000, interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }


            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.OnTick();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
        if (this.callback != null) {
            if (setting == TimeSettings.MILLISEC) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay,interval);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay);
                    return;
                }
            }

            if (setting == TimeSettings.SECOND) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 1000,interval * 1000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 1000);
                    return;
                }
            }

            if (setting == TimeSettings.MINUTE) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 60000, interval * 60000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 60000);
                    return;
                }
            }

            if (setting == TimeSettings.HOUR) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 3600000, interval * 3600000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 3600000);
                    return;
                }
            }

            if (setting == TimeSettings.DAY) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 86400000, interval * 86400000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 86400000);
                    return;
                }
            }

            if (this.setting == TimeSettings.WEEK) {
                if (this.interval > 0) {
                    timer.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                            }
                        }
                    }, delay * 604800000, interval * 604800000);
                    return;
                } else {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (DefaultTimer.this.debug) {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                OnDebug("Task State: " + DefaultTimer.this.getState().toString() + " Timestamp: " + new Date());
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            } else {
                                DefaultTimer.this.setState(TimerStates.COMPLETED);
                                DefaultTimer.this.callback.OnCallback();
                                DefaultTimer.this.timer.cancel();
                            }
                        }
                    }, delay * 604800000);
                    return;
                }
            }
        }
    }

    /**
     * Abstract method that is called at various intervals based on the constructor.
     */
    public void OnTick() {
        System.out.println("OnTick");
    }

    /**
     * You must implement this method in order to receive debug messages. 
     * @param message
     */
    public void OnDebug(String message) {
    }
    
    public TimerStates getState(){
        return this.state;
    }

    /**
     * Stops the repeating Timer.
     */
    public void Stop() {
        this.setState(TimerStates.STOPPED);
        this.timer.cancel();
    }

    /**
     * @param state the state to set
     */
    public void setState(TimerStates state) {
        this.state = state;
    }

    public enum TimerStates {

        READY,
        STARTED,
        RESUMED,
        COMPLETED,
        PAUSED,
        STOPPED
    }

    public enum TimeSettings {

        MILLISEC,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK,
    }
}
