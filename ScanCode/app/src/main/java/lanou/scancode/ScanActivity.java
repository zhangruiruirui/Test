package lanou.scancode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.URIParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Intents;
import com.mylhyl.zxing.scanner.result.AddressBookResult;

/**
 * Created by ZhangRui on 16/11/8.
 */
public class ScanActivity extends AppCompatActivity {

    private ScannerView scannerView;
    private Bundle bundle;
    public static final String SCAN_RESULT_KEK = "result";
    public static final int RESULT = 102;

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scannerView = (ScannerView) findViewById(R.id.scan_scan);
        bundle = new Bundle();
        scannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result result, ParsedResult parsedResult, Bitmap bitmap) {
                ParsedResultType parsedResultType = parsedResult.getType();
                switch (parsedResultType) {
                    case ADDRESSBOOK:
                        AddressBookParsedResult addressBook = (AddressBookParsedResult) parsedResult;
                        bundle.putSerializable(Intents.Scan.RESULT, new AddressBookResult(addressBook));
                        break;
                    case URI:
                        URIParsedResult uriParsedResult = (URIParsedResult) parsedResult;
                        bundle.putString(Intents.Scan.RESULT, uriParsedResult.getURI());
                        break;
                    case TEXT:
                        bundle.putString(Intents.Scan.RESULT, result.getText());
                        break;
                }
                Intent intent = getIntent();
                intent.putExtra("bundle",bundle);
                setResult(RESULT,intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        scannerView.onResume();
        super.onResume();
    }
}
