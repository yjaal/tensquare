package win.iot4yj.friend.service;

import org.springframework.stereotype.Service;

/**
 * Description:
 * date: 2018/12/4 17:23
 * author: loveLy
 */
@Service
public interface FriendService {

	void deleteFriend(String userid, String friendid);

	//不喜欢列表中添加记录
	void addNoFriend(String userid, String friendid);

	//喜欢列表中添加记录
	int addFriend(String userid, String friendid);
}
