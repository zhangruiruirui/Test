package lanou.lldh;

/**
 * Created by succlz123 on 15/11/20.
 */
public interface OkDownloadCancelListener {

    void onCancel();

    void onError(OkDownloadError error);
}
