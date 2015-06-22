package com.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.VO.HeartBeat;

public class HeartBeatThread extends Thread {

	public volatile Boolean flag = true;
	private Connection connection;

	public HeartBeatThread(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		List<HeartBeat> StateList = new ArrayList<HeartBeat>();
		
		while (flag) {
			try {
				// �Ȼ�ȡ��������
				PreparedStatement pstmt = null;
				ResultSet resultSet = null;
				connection.createStatement();
				String sql = "select * from userdata where Online='true'";
				pstmt = connection.prepareStatement(sql);
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					HeartBeat beat = new HeartBeat();
					beat.setUsername(resultSet.getString("User_name"));
					beat.setHeart(resultSet.getInt("HeartBeat"));
					beat.setOnline(resultSet.getString("Online"));
					StateList.add(beat);
				}

				// �ж��ĸ���5������״̬��Ϊfalse
				for (int i = 0; i < StateList.size(); i++) {
					if (StateList.get(i).getHeart()==5) {
						pstmt = null;
						resultSet = null;
						sql = "update userdata set Online = 'false' where User_name=?";
						pstmt = connection.prepareStatement(sql);
						pstmt.setString(1, StateList.get(i).getUsername());
						pstmt.executeUpdate();
					}
				}
				

				// ������һд�����ݿ�
				for (int i = 0; i < StateList.size(); i++) {
					int heat = StateList.get(i).getHeart() + 1;
					pstmt = null;
					resultSet = null;
					sql = "update userdata set HeartBeat = ? where User_name=?";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, heat);
					pstmt.setString(2, StateList.get(i).getUsername());
					pstmt.executeUpdate();
				}
				sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}

}
