package org.talestats.dao;

import org.talestats.model.Scheduler;

public interface SchedulerDAO {

    public void addScheduler();
    public void deleteScheduler();
    public Scheduler getScheduler();
    
}