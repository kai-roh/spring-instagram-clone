package com.cos.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying // for INSERT, DELETE, UPDATE in native query
    @Query(value = "INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId); // 1(success, number of changed row), 0(not executed), -1(fail)

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId); // 1, -1

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principlaId AND toUserId = :pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);

}
