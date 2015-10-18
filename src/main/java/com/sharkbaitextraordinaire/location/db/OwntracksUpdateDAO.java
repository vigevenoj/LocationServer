package com.sharkbaitextraordinaire.location.db;

import com.sharkbaitextraordinaire.location.core.OwntracksUpdate;
import com.sharkbaitextraordinaire.location.core.OwntracksUpdateMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(OwntracksUpdateMapper.class)
public interface OwntracksUpdateDAO {
	
	@SqlUpdate("create table owntracksupdates (_type varchar(10), lat varchar(12), lon varchar(12), acc varchar(8), tst bigint, batt varchar(8) )")
	void createTableIfNotExists();
	
	@SqlQuery("select _type, lat, lon, acc, tst, batt from owntracksupdates order by tst asc")
	List<OwntracksUpdate> findAll();
	
	@SqlQuery("select _type, lat, lon, acc, tst, batt from owntracksupdates order by tst desc limit 1")
	OwntracksUpdate findLatest();
	
	@SqlQuery("select _type, lat, lon, acc, tst, batt from owntracksupdates where tst > :tst")
	List<OwntracksUpdate> findSinceTimestamp(@Bind("tst") Long tst);
	
	@SqlUpdate("insert into owntracksupdates (_type, lat, lon, acc, tst, batt) values (:_type, :lat, :lon, :acc, :tst, :batt)")
	void insert(@Bind("_type") String _type, @Bind("lat") String lat, @Bind("lon") String lon, @Bind("acc") String acc, @Bind("tst") Long tst, @Bind("batt") String batt);
	
	@SqlUpdate("delete from owntracksupdates")
	void deleteAll();

	void close();
}
