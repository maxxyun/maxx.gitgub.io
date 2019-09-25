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
	boolean check;	//ckeck 가 true이면 사용한 것이므로 다시 계산해서 다른 것을 쓰드록 한다./
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
//		System.out.format("시간", timeBuffer);

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

//	static int expCount = 0;	//실험 횟수
ArrayList<Integer> check;		// 두 맵의 값을 확인합니다.
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
	      
	      
	      //초기화
	      onOff = 0;
	      reply = 0;
	      check = new ArrayList<Integer>();
	      btnList = new ArrayList<Mapping>();
	      spaceList = new ArrayList<Mapping>();
	      
	       
	      //버튼과 공간에 대한 인스턴스 가져온다
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
	        
	        //시작 화면도 랜덤하게!
	        Random rnd = new Random();
	        
	        btnHeight = rnd.nextInt() % 4;
			spaceHeight = rnd.nextInt() % 4;
			
			if(btnHeight < 0)
				btnHeight = btnHeight * (-1);
			if(spaceHeight < 0)
				spaceHeight = spaceHeight * (-1);	        
			
			check.add(btnList.get(btnHeight).value + spaceList.get(spaceHeight).value);
			
			//버튼과 공간의 크기를 새로 뿌린다.
			btn1.setHeight(btnList.get(btnHeight).key);
			btn2.setHeight(btnList.get(btnHeight).key);
			text.setHeight(spaceList.get(spaceHeight).key);
			
			space_height =  spaceList.get(spaceHeight).key;
			button_height = btnList.get(btnHeight).key;
			
	        // 버튼 1을 클릭하면 이벤트가 발생합니다. 이벤트는 public void onClick(View)에 기술합니다.
	        btn1.setOnClickListener(this);
	        
		      
	}
	
	public void onClick(View v) {
		

		click_number++; //상단 버튼을 눌렀으니 클릭 횟수를 1 증가시킨다.
		
	     //버튼과 공간에 대한 인스턴스 가져온다
		text = (TextView)findViewById(R.id.text);	
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
		
		Random rnd = new Random();

		
		/**
		 * 3회 실험을 했을 때 = 상단 버튼을 6번 눌렀을 때
		 * 버튼과 공간의 배치를 랜덤으로 다시 뿌려줍니다
		 * 이전에 실험한 값과 비교하여 같으면 동일한 시험이므로 다시 랜덤을 돌리고
		 * 이전과 실험한 값과 다르면 새로운 실험이므로 그걸 보여줍니다.
		 */

		if((click_number % 6) == 0)	{ 

			//어떤 랜덤화면을 보여줄지 계산한다.
			//이전과 같은 값이라면 다시 계산하고 다른 값이면 저장하고, while문을 빠져나간다.
			while(true)	{
				btnHeight = rnd.nextInt() % 4;
				spaceHeight = rnd.nextInt() % 4;
				
				if(btnHeight < 0)
					btnHeight = btnHeight * (-1);
				if(spaceHeight < 0)
					spaceHeight = spaceHeight * (-1);
				
				if(check.equals(btnList.get(btnHeight).value + spaceList.get(spaceHeight).value))	{
					continue; // 이전에 한 실험과 같은 상황이므로 다시 랜덤을 돌린다.
				}
				else	{
					// 이전에 한 실험과 다른 값을 가지므로 while문을 벗어나서 새로운 랜덤화면을 보여준다.
					check.add(btnList.get(btnHeight).value + spaceList.get(spaceHeight).value);
					break;
				}
			}

		}


		
		/**
		 * 시간을 측정합니다.
		 */
		if(onOff == 0 )	{
			second = System.currentTimeMillis();

			onOff = 1;
//			btn1.setText(String.format("%f", second));

		} else 	{
			onOff = 0;
			reply++; // 실험을 몇번 시도하였는지
			second =  System.currentTimeMillis() - second;	
			text.setText(reply + "번째 실험 : " + second +" ms\n" + "버튼넓이 : " + button_height + 
					"\n빈칸넓이 : " + space_height);
			
			//실험 데이터를 축적합니다
			sb.append(reply + "번째 실험 : " + second +" ms\n" + "버튼넓이 : " + button_height + 
					"\n빈칸넓이 : " + space_height + "\n\n");
			//Toast.makeText(ReceiveLabActivity.this, reply + "번째 실험 : " + second +" ms", Toast.LENGTH_SHORT).show();
			
		}
		
		//버튼과 공간의 크기를 새로 뿌린다.
		btn1.setHeight(btnList.get(btnHeight).key);
		btn2.setHeight(btnList.get(btnHeight).key);
		text.setHeight(spaceList.get(spaceHeight).key);
		
		space_height =  spaceList.get(spaceHeight).key;
		button_height = btnList.get(btnHeight).key;

		
		
		// 실험 종료
		if(reply == 48)	{
			/**
			 * 총 실험은 16번 이므로
			 * 16회 초과한 것은 의미가 없으므로 종료한다.
			 */
			AlertDialog.Builder dlg = new AlertDialog.Builder(ReceiveLabActivity.this);
			
			dlg.setMessage(sb);
			dlg.setPositiveButton("확인",
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



