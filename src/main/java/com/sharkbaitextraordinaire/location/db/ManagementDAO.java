package com.sharkbaitextraordinaire.location.db;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ManagementDAO {
	
	@SqlUpdate("create table managementProperties (propname varchar(256), propvalue varchar(256) )")
	void createTableIfNotExists();
	
	@SqlUpdate("select propvalue from managementProperties where propname = 'trackingEnabled'")
	boolean trackingEnabled();
	
	@SqlUpdate("update managementProperties set propvalue = 'true' where propname = 'trackingEnabled'")
	void enableTracking(boolean enable);
	
	@SqlUpdate("update managementProperties set propvalue = 'false' where propname = 'trackingEnabled'")
	void disableTracking();

}
