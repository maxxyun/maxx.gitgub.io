package hg.master;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

class	Mapping{
	int key;
	int value;
	boolean check;	//ckeck �� true�̸� ����� ���̹Ƿ� �ٽ� ����ؼ� �ٸ� ���� ����� �Ѵ�./
	public Mapping(int key, int value)	{
		this.key = key;
		this.value = value;
		check = false;
	}
}

class Test	{
	static String timeBuffer;
	static int oldTime;
	public Test() {
		stopwatch(1);
		pause();
		stopwatch(0);
//		System.out.format("�ð�", timeBuffer);

	}

	public static void stopwatch(int onOff) {
		if (onOff == 1)
			oldTime = (int) System.currentTimeMillis();
		if (onOff == 0)
			sec(((int) System.currentTimeMillis()) - oldTime);
	}

	public static void sec(int secs) {
		int sec;
		sec = secs % 60;
		timeBuffer = String.format("%10d", sec);
	}

	public static void pause() {
		{
			try {
				System.in.read();
			} catch (IOException e) {
			}
		}
	}	
}

public class ReceiveLabActivity extends Activity implements OnClickListener {

int onOff ;
static long second;
int reply ;
int click_number;

//	static int expCount = 0;	//���� Ƚ��
ArrayList<Integer> check;		// �� ���� ���� Ȯ���մϴ�.
 ArrayList<Mapping> btnList ;
ArrayList<Mapping> spaceList;
StringBuffer sb;
int button_height, space_height;

	Button btn1, btn2;
	TextView text;
	int btnHeight;
	int spaceHeight;
	

	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.receive);
	      
	      sb = new StringBuffer();
	      
	      
	      //�ʱ�ȭ
	      onOff = 0;
	      reply = 0;
	      check = new ArrayList<Integer>();
	      btnList = new ArrayList<Mapping>();
	      spaceList = new ArrayList<Mapping>();
	      
	       
	      //��ư�� ������ ���� �ν��Ͻ� �����´�
			text = (TextView)findViewById(R.id.text);	
			btn1=(Button)findViewById(R.id.button1);
			btn2=(Button)findViewById(R.id.button2);
	      
	        btnList.add(new Mapping(100, 1));
	        btnList.add(new Mapping(200, 2));
	        btnList.add(new Mapping(300, 3));
	        btnList.add(new Mapping(400, 4));
	        
	        spaceList.add(new Mapping(100, 10));
	        spaceList.add(new Mapping(200, 20));
	        spaceList.add(new Mapping(300, 30));
	        spaceList.add(new Mapping(400, 40));
	        
	        //���� ȭ�鵵 �����ϰ�!
	        Random rnd = new Random();
	        
	        btnHeight = rnd.nextInt() % 4;
			spaceHeight = rnd.nextInt() % 4;
			
			if(btnHeight < 0)
				btnHeight = btnHeight * (-1);
			if(spaceHeight < 0)
				spaceHeight = spaceHeight * (-1);	        
			
			check.add(btnList.get(btnHeight).value + spaceList.get(spaceHeight).value);
			
			//��ư�� ������ ũ�⸦ ���� �Ѹ���.
			btn1.setHeight(btnList.get(btnHeight).key);
			btn2.setHeight(btnList.get(btnHeight).key);
			text.setHeight(spaceList.get(spaceHeight).key);
			
			space_height =  spaceList.get(spaceHeight).key;
			button_height = btnList.get(btnHeight).key;
			
	        // ��ư 1�� Ŭ���ϸ� �̺�Ʈ�� �߻��մϴ�. �̺�Ʈ�� public void onClick(View)�� ����մϴ�.
	        btn1.setOnClickListener(this);
	        
		      
	}
	
	public void onClick(View v) {
		

		click_number++; //��� ��ư�� �������� Ŭ�� Ƚ���� 1 ������Ų��.
		
	     //��ư�� ������ ���� �ν��Ͻ� �����´�
		text = (TextView)findViewById(R.id.text);	
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
		
		Random rnd = new Random();

		
		/**
		 * 3ȸ ������ ���� �� = ��� ��ư�� 6�� ������ ��
		 * ��ư�� ������ ��ġ�� �������� �ٽ� �ѷ��ݴϴ�
		 * ������ ������ ���� ���Ͽ� ������ ������ �����̹Ƿ� �ٽ� ������ ������
		 * ������ ������ ���� �ٸ��� ���ο� �����̹Ƿ� �װ� �����ݴϴ�.
		 */

		if((click_number % 6) == 0)	{ 

			//� ����ȭ���� �������� ����Ѵ�.
			//������ ���� ���̶�� �ٽ� ����ϰ� �ٸ� ���̸� �����ϰ�, while���� ����������.
			while(true)	{
				btnHeight = rnd.nextInt() % 4;
				spaceHeight = rnd.nextInt() % 4;
				
				if(btnHeight < 0)
					btnHeight = btnHeight * (-1);
				if(spaceHeight < 0)
					spaceHeight = spaceHeight * (-1);
				
				if(check.equals(btnList.get(btnHeight).value + spaceList.get(spaceHeight).value))	{
					continue; // ������ �� ����� ���� ��Ȳ�̹Ƿ� �ٽ� ������ ������.
				}
				else	{
					// ������ �� ����� �ٸ� ���� �����Ƿ� while���� ����� ���ο� ����ȭ���� �����ش�.
					check.add(btnList.get(btnHeight).value + spaceList.get(spaceHeight).value);
					break;
				}
			}

		}


		
		/**
		 * �ð��� �����մϴ�.
		 */
		if(onOff == 0 )	{
			second = System.currentTimeMillis();

			onOff = 1;
//			btn1.setText(String.format("%f", second));

		} else 	{
			onOff = 0;
			reply++; // ������ ��� �õ��Ͽ�����
			second =  System.currentTimeMillis() - second;	
			text.setText(reply + "��° ���� : " + second +" ms\n" + "��ư���� : " + button_height + 
					"\n��ĭ���� : " + space_height);
			
			//���� �����͸� �����մϴ�
			sb.append(reply + "��° ���� : " + second +" ms\n" + "��ư���� : " + button_height + 
					"\n��ĭ���� : " + space_height + "\n\n");
			//Toast.makeText(ReceiveLabActivity.this, reply + "��° ���� : " + second +" ms", Toast.LENGTH_SHORT).show();
			
		}
		
		//��ư�� ������ ũ�⸦ ���� �Ѹ���.
		btn1.setHeight(btnList.get(btnHeight).key);
		btn2.setHeight(btnList.get(btnHeight).key);
		text.setHeight(spaceList.get(spaceHeight).key);
		
		space_height =  spaceList.get(spaceHeight).key;
		button_height = btnList.get(btnHeight).key;

		
		
		// ���� ����
		if(reply == 48)	{
			/**
			 * �� ������ 16�� �̹Ƿ�
			 * 16ȸ �ʰ��� ���� �ǹ̰� �����Ƿ� �����Ѵ�.
			 */
			AlertDialog.Builder dlg = new AlertDialog.Builder(ReceiveLabActivity.this);
			
			dlg.setMessage(sb);
			dlg.setPositiveButton("Ȯ��",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub

						}
					});
			dlg.show();		
		}

		
	}
	

}



