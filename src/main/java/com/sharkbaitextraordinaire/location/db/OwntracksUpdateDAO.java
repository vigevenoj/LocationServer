package com.sharkbaitextraordinaire.location.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.Bind;

import com.sharkbaitextraordinaire.location.core.OwntracksUpdate;

public interface OwntracksUpdateDAO {
	
	@SqlUpdate("create table owntracksupdates (_type varchar(10), lat varchar(12), lon varchar(12), acc smallint, tst bigint")
	void createTableIfNotExists();
	
	@SqlQuery("select _type, lat, lon, acc, tst from owntracksupdates order by tst desc")
	List<OwntracksUpdate> findAll();
	
	@SqlQuery("select _type, lat, lon, acc, tst from owntracksupdates order by tst desc limit 1")
	OwntracksUpdate findLatest();
	
	@SqlUpdate("insert into owntracksupdates (_type, lat, lon, acc, tst) values (:_type, :lat, :lon, :acc, :tst)")
	void insert(@Bind("_type") String _type, @Bind("lat") String lat, @Bind("lon") String lon, @Bind("acc") Short acc, @Bind("tst") Long tst);
	
	void close();
}
