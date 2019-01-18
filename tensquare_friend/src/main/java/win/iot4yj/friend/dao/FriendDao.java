package win.iot4yj.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import win.iot4yj.friend.pojo.Friend;

/**
 * Description:
 * date: 2018/12/4 17:16
 * author: loveLy
 */
public interface FriendDao extends JpaRepository<Friend, String> {

	/**
	 * 根据用户ID和被关注用户ID查询记录个数
	 *
	 * @param userid
	 * @param friendid
	 * @return
	 */
	@Query("select count(f) from Friend f where f.userid=?1 and f.friendid=?2")
	int selectCount(String userid, String friendid);

	/**
	 * 更新为互相喜欢
	 *
	 * @param userid
	 * @param friendid
	 * @param islike
	 */
	@Modifying
	@Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
	void updateLike(String userid, String friendid, String islike);


	/**
	 * 删除喜欢
	 *
	 * @param userid
	 * @param friendid
	 */
	@Modifying
	@Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
	void deleteFriend(String userid, String friendid);
}
