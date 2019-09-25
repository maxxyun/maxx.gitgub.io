package hg.master;


/**
 * KetStore : ��ȫ��
 * HG_Master key : �迵��
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
     // ��ư�� ������ �̺�Ʈ
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
			str_sex = "��";
		}
		if (rg_sex.getCheckedRadioButtonId() == R.id.radio_female) {
			str_sex = "��";
		}
		

		
		//ȣ�� Ŭ����
		Intent it = new Intent(this, ReceiveLabActivity.class);
		
		//���� ����
		it.putExtra("it_name", str_name);
		it.putExtra("it_sex", str_sex);
		it.putExtra("it_age", str_age);
//		it.putExtra("it_kinds", str_kinds);
		
		startActivity(it);
		
		finish();
	}
}
