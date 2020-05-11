package com.livestream.controller;

//import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.member_follow.model.MemberFollowService;
import com.notice.model.NoticeService;

public class TimerTest extends TimerTask {
	private String followed = "";
	//private Date liveDate = null;
	public TimerTest(String followed) {
		super();
		this.followed = followed;
		//this.liveDate = liveDate;
	}

@Override 
public void run() { 
//notice
NoticeService noticeSvc = new NoticeService();
MemberFollowService mfSvc = new MemberFollowService();
String[] m_ids = mfSvc.getAllMemberByFollowed(followed);
for (String m_id : m_ids) {
	noticeSvc.insert(m_id, 4, "您追蹤的廚師今天會開播喔！", 0);
}
}

	public static void main(String[] args) {
		Timer timer = new Timer();
		long delay1 = 1 * 5000;
	//	long period1 = 5000;
// 從現在開始 1 秒鐘之後，每隔 1 秒鐘執行一次 job1 
		timer.schedule(new TimerTest("job1"), delay1);
//		long delay2 = 2 * 1000;
//		long period2 = 2000;
//// 從現在開始 2 秒鐘之後，每隔 2 秒鐘執行一次 job2 
//		timer.schedule(new TimerTest("job2"), delay2, period2);
	}
}
