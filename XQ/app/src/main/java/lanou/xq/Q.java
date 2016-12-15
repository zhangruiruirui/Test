package lanou.xq;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;
import com.turing.androidsdk.asr.VoiceRecognizeListener;
import com.turing.androidsdk.asr.VoiceRecognizeManager;
import com.turing.androidsdk.tts.TTSListener;
import com.turing.androidsdk.tts.TTSManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

public class Q extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = Q.class.getSimpleName();
    private TTSManager ttsManager;
    private TTSManager ttsManagerTwo;
    private VoiceRecognizeManager recognizerManager;
    private TuringApiManager mTuringApiManager;
    private Button mStatus;
    private EditText mStatusEt;
    private Button mStatusBtn;
    /**
     * 返回结果，开始说话
     */
    public final int SPEECH_START = 0;
    public final int SPEECH_START_TWO = 4;
    /**
     * 开始识别
     */
    public final int RECOGNIZE_RESULT = 1;
    /**
     * 开始识别
     */
    public final int RECOGNIZE_START = 2;
    public final int WRITE_START = 3;

    /**
     * 申请的turing的apikey
     **/
    private final String TURING_APIKEY = "70af43f4fecc45109483d691c6df2249";
    private final String TURING_APIKEY_TWO = "a15da4fa3f2840d09accef68d3f69b4c";

    /**
     * 申请的secret
     **/
    private final String TURING_SECRET = "b207a7f7a669a2dc";
    private final String TURING_SECRET_TWO = "0f1ef8fecf567ded";
    /**
     * 填写一个任意的标示，没有具体要求，，但一定要写，
     **/
    private final String UNIQUEID = "8933680";
    //百度key
    private final String BD_APIKEY = "kzlNupi8iTUZldbNmCk7c01t";
    //百度screte
    private final String BD_SECRET = "91c1c731195143a93d5f255a6329fcbc";

    private Handler myHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SPEECH_START:
                    ttsManager.startTTS((String) msg.obj);
                    mStatus.setText("开始讲话：" + (String) msg.obj);
                    mStatusBtn.setText("文字识别:" + (String) msg.obj);
                    break;
                case RECOGNIZE_RESULT:
                    mStatus.setText("识别结果：" + msg.obj);
                    mStatusBtn.setText("识别结果" + msg.obj);
                    break;
                case RECOGNIZE_START:
                    mStatus.setText("开始识别");
                    break;
                case WRITE_START:
                    // // TODO: 16/11/23
                    mStatusBtn.setText("开始识别");
                    break;
                case SPEECH_START_TWO:
                    ttsManagerTwo.startTTS((String) msg.obj);
                    mStatus.setText("开始讲话：" + (String) msg.obj);
                    break;
                default:
                    break;
            }
        }


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);
        Intent intent = getIntent();
        String afff = intent.getStringExtra("key");
        if (afff == null){
            ttsManagerTwo.startTTS("你好");

        Log.d("Q111", afff);
        }

        mStatus = (Button) findViewById(R.id.tv_status);
        mStatusEt = (EditText) findViewById(R.id.ed_status);
        mStatusBtn = (Button) findViewById(R.id.btn_status);
        mStatusBtn.setOnClickListener(this);
        init();

    }

    /**
     * 初始化turingSDK、识别和tts
     */
    private void init() {
        /** 支持百度、讯飞，需自行去相关平台申请appid，并导入相应的jar和so文件 */

        recognizerManager = new VoiceRecognizeManager(this, BD_APIKEY, BD_SECRET);
        ttsManager = new TTSManager(this, BD_APIKEY, BD_SECRET);
        ttsManagerTwo = new TTSManager(this,BD_APIKEY,BD_SECRET);
        recognizerManager.setVoiceRecognizeListener(myVoiceRecognizeListener);
        ttsManager.setTTSListener(myTTSListener);
        ttsManagerTwo.setTTSListener(myTTSListener);
        // turingSDK初始化
        SDKInitBuilder builder = new SDKInitBuilder(this)
                .setSecret(TURING_SECRET).setTuringKey(TURING_APIKEY).setTuringKey(TURING_APIKEY_TWO).
                        setSecret(TURING_SECRET_TWO).setUniqueId(UNIQUEID);
        SDKInit.init(builder, new InitListener() {
            @Override
            public void onFail(String error) {
                Log.d(TAG, error);
            }

            @Override
            public void onComplete() {
                // 获取userid成功后，才可以请求Turing服务器，需要请求必须在此回调成功，才可正确请求
                mTuringApiManager = new TuringApiManager(Q.this);
                mTuringApiManager.setHttpListener(myHttpConnectionListener);
                ttsManager.startTTS("你好啊我是群仔仔");
                ttsManagerTwo.startTTS("你好");//// TODO: 16/11/25
            }
        });
    }

    /**
     * 网络请求回调
     */
    HttpConnectionListener myHttpConnectionListener = new HttpConnectionListener() {

        @Override
        public void onSuccess(RequestResult result) {
            if (result != null) {
                try {
                    Log.d(TAG, result.getContent().toString());
                    JSONObject result_obj = new JSONObject(result.getContent()
                            .toString());
                    if (result_obj.has("text")) {
                        Log.d(TAG, result_obj.get("text").toString());
                        myHandler.obtainMessage(SPEECH_START,
                                result_obj.get("text")).sendToTarget();
                        myHandler.obtainMessage(SPEECH_START_TWO,
                                result_obj.get("text")).sendToTarget();//// TODO: 16/11/25
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "JSONException:" + e.getMessage());
                }
            }
        }

        @Override
        public void onError(ErrorMessage errorMessage) {
            Log.d(TAG, errorMessage.getMessage());
        }
    };

    /**
     * 语音识别回调
     */
    VoiceRecognizeListener myVoiceRecognizeListener = new VoiceRecognizeListener() {

        @Override
        public void onVolumeChange(int volume) {
            // 仅讯飞回调
        }

        @Override
        public void onStartRecognize() {
            // 仅针对百度回调
        }

        @Override
        public void onRecordStart() {

        }

        @Override
        public void onRecordEnd() {

        }

        @Override
        public void onRecognizeResult(String result) {
            Log.d(TAG, "识别结果：" + result);
            if (result == null) {
                recognizerManager.startRecognize();
                myHandler.sendEmptyMessage(RECOGNIZE_START);
                return;
            }
            mTuringApiManager.requestTuringAPI(result);
            myHandler.obtainMessage(RECOGNIZE_RESULT, result).sendToTarget();

        }

        @Override
        public void onRecognizeError(String error) {
            Log.e(TAG, "识别错误：" + error);
            recognizerManager.startRecognize();
            myHandler.sendEmptyMessage(RECOGNIZE_START);

        }
    };

    /**
     * TTS回调
     */
    TTSListener myTTSListener = new TTSListener() {

        @Override
        public void onSpeechStart() {
            Log.d(TAG, "TTS Start!");
        }

        @Override
        public void onSpeechProgressChanged() {

        }

        @Override
        public void onSpeechPause() {
            Log.d(TAG, "TTS Pause!");
        }

        @Override
        public void onSpeechFinish() {
            recognizerManager.startRecognize();
            myHandler.obtainMessage(RECOGNIZE_START).sendToTarget();
        }

        @Override
        public void onSpeechError(int errorCode) {
            Log.d(TAG, "TTS错误，错误码：" + errorCode);
        }


        @Override
        public void onSpeechCancel() {
            Log.d(TAG, "TTS Cancle!");
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_status:
                // // TODO: 16/11/23  文字识别没成功,机器人变成了复读机


                String s = mStatusEt.getText().toString();
                mTuringApiManager.requestTuringAPI(s);
                myHandler.obtainMessage(RECOGNIZE_RESULT, s).sendToTarget();
                break;

        }
    }


}
