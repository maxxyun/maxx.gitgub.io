package hg.master;


/**
 * KetStore : 윤홍구
 * HG_Master key : 김영훈
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class HG_MasterActivity extends Activity implements OnClickListener{
	Spinner spinner;
    /** Called when the activity is first created. */
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button btn=(Button)findViewById(R.id.button_send);
     // 버튼을 누르는 이벤트
        btn.setOnClickListener(this);
    }
	
	public void onClick(View v) {
		EditText et_name = (EditText)findViewById(R.id.edit_name);
		String str_name = et_name.getText().toString();
		
		EditText et_age = (EditText)findViewById(R.id.edit_age);
		String str_age = et_age.getText().toString();
		
		RadioGroup rg_sex = (RadioGroup)findViewById(R.id.radiogroup_sex);
		String str_sex = "";
		if (rg_sex.getCheckedRadioButtonId() == R.id.radio_male) {
			str_sex = "남";
		}
		if (rg_sex.getCheckedRadioButtonId() == R.id.radio_female) {
			str_sex = "여";
		}
		

		
		//호출 클래스
		Intent it = new Intent(this, ReceiveLabActivity.class);
		
		//전송 정보
		it.putExtra("it_name", str_name);
		it.putExtra("it_sex", str_sex);
		it.putExtra("it_age", str_age);
//		it.putExtra("it_kinds", str_kinds);
		
		startActivity(it);
		
		finish();
	}
}
