package com.application.easysave;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Map<Integer, Integer> editTextMap = new HashMap<Integer, Integer>();

	private final TextWatcher editTextWatcher = new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// System.out.println(s + " " + start + " " + before + " " + count);

		}

		@Override
		public void afterTextChanged(Editable s) {
		}

		private final boolean isPhoneNumber(CharSequence charSequence) {
			if (charSequence == null || TextUtils.isEmpty(charSequence)) {
		        return false;
		    } else {
		        return android.util.Patterns.PHONE.matcher(charSequence).matches();
		    }
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		EditText ed;
		TextView tv;
		TextView tv1;
		TableLayout tableLayout = (TableLayout) findViewById(R.id.easySaveTableLayout);

		TableRow tableRow;
		int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				(float) 1, this.getResources().getDisplayMetrics());
		for (int i = 0; i < 50; i++) {
			tableRow = new TableRow(this);
			tableRow.setPadding(8, 8, 8, 8);
			tableRow.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			tv = new TextView(this);
			tv.setId(i);
			tv.setText("Save");
			tv.setWidth(35 * dip);
			tv.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			ed = new EditText(this);
			ed.setId(i);
			ed.setWidth(225 * dip);
			ed.addTextChangedListener(editTextWatcher);
			editTextMap.put(i, 0);
			ed.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			tv1 = new TextView(this);
			tv1.setId(i);
			tv1.setText("Discard");
			tv1.setWidth(70 * dip);
			tv1.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			tableRow.addView(tv);
			tableRow.addView(ed);
			tableRow.addView(tv1);

			tableLayout.addView(tableRow, new TableLayout.LayoutParams(
					TableLayout.LayoutParams.WRAP_CONTENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
		}

		View view = tableLayout.findFocus();

		if (view instanceof EditText) {
			int id = view.getId();
			EditText editText = (EditText) findViewById(id);
			editText.addTextChangedListener(editTextWatcher);
		}

	}
}