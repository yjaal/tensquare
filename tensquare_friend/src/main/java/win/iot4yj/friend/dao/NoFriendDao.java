package win.iot4yj.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import win.iot4yj.friend.pojo.NoFriend;

/**
 * Description:
 * date: 2018/12/4 19:37
 * author: loveLy
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {

	//不喜欢列表数据
}
