package org.fofaviewer.controllers;

import java.io.BufferedReader;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.controlsfx.control.StatusBar;
import org.controlsfx.dialog.CommandLinksDialog;
import org.controlsfx.dialog.ProgressDialog;
import org.fofaviewer.bean.*;
import org.fofaviewer.callback.SaveOptionCallback;
import org.fofaviewer.controls.*;
import org.fofaviewer.main.FofaConfig;
import org.fofaviewer.callback.MainControllerCallback;
import org.fofaviewer.request.Request;
import org.fofaviewer.callback.RequestCallback;
import org.fofaviewer.utils.DataUtil;
import org.fofaviewer.utils.RequestUtil;
import org.controlsfx.control.textfield.TextFields;
import org.fofaviewer.utils.ResourceBundleUtil;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import org.fofaviewer.utils.SQLiteUtils;
import org.tinylog.Logger;



public class MainController {
    private Map<String, Object> projectInfo;
    private AutoHintTextField decoratedField;
    private static final RequestUtil helper = RequestUtil.getInstance();
    private FofaConfig client;
    private final ResourceBundle resourceBundle;
    private final HashMap<CheckBox, String> keyMap = new HashMap<>();
    @FXML
    private Menu help;
    @FXML
    private Menu project;

    @FXML
    private Menu urlFunction;

    @FXML
    private Menu urlCombination;

    @FXML
    private Menu awvs;

    @FXML
    private Menu urlslice;

    @FXML
    private MenuItem importFile;

    @FXML
    private MenuItem urlslicein;

    @FXML
    private Menu no_repeat;

    @FXML
    private MenuItem no_repeat_id;

    @FXML
    private MenuItem urlCsettings;

    @FXML
    private MenuItem awvsTran;





//    @FXML
//    private MenuItem startOn;

    @FXML
    private Menu rule;
    @FXML
    private Menu config;
//    @FXML
//    private MenuItem query_api;
//    @FXML
//    private MenuItem ;
//    @FXML
//    private MenuItem exportRule;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem setConfig;
    @FXML
    private MenuItem openProject;
    @FXML
    private MenuItem saveProject;
    @FXML
    private Button exportDataBtn;

    @FXML
    private Button exportAll;

    @FXML
    private Button searchBtn;
    @FXML
    private Label queryString;
    @FXML
    private VBox rootLayout;
    @FXML
    private TextField queryTF;
//    @FXML
//    private CheckBox checkHoneyPot;
//    @FXML
//    private CheckBox withFid;

//    @FXML
//    private CheckBox hostOnly;  // 仅host

    @FXML
    private CheckBox isAll;
//    @FXML
//    private CheckBox title;
//    @FXML
//    private CheckBox cert;
    @FXML
    private CloseableTabPane tabPane;

    public MainController(){
        this.resourceBundle = ResourceBundleUtil.getResource();
    }




    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        SQLiteUtils.init();
//        keyMap.put(withFid, "fid");
//        keyMap.put(hostOnly, "123host");
//        keyMap.put(cert, "cert");
//        keyMap.put(title, "title");
        projectInfo = new HashMap<>();
        projectInfo.put("status", Boolean.FALSE);
        projectInfo.put("name", "");
//        title.setText(resourceBundle.getString("TITLE"));
//        cert.setText(resourceBundle.getString("CERT"));
        about.setText(resourceBundle.getString("ABOUT"));
        help.setText(resourceBundle.getString("HELP"));
        project.setText(resourceBundle.getString("PROJECT"));

        urlFunction.setText(resourceBundle.getString("URLFUNCTION"));
        no_repeat_id.setText(resourceBundle.getString("no_repeat_id"));

//        @FXML
//        private Menu urlFunction;
//
//        @FXML
//        private Menu importTxt;
        importFile.setText(resourceBundle.getString("IMPORTFILE"));
//        startOn.setText(resourceBundle.getString("STARTFILE"));

        //urlCombination
        urlCombination.setText(resourceBundle.getString("URLCOMBINATION"));
        urlCsettings.setText(resourceBundle.getString("URLCSETTINGS"));

        awvsTran.setText(resourceBundle.getString("AWVSTRAN"));


        urlslicein.setText(resourceBundle.getString("urlslicein"));




        awvs.setText(resourceBundle.getString("AWVS"));

        urlslice.setText(resourceBundle.getString("urlslice"));


        no_repeat.setText(resourceBundle.getString("no_repeat"));


        config.setText(resourceBundle.getString("CONFIG_PANEL"));
//        rule.setText(resourceBundle.getString("RULE"));
//        query_api.setText(resourceBundle.getString("QUERY_API"));
        setConfig.setText(resourceBundle.getString("SET_CONFIG"));
//        createRule.setText(resourceBundle.getString("CREATE_RULE"));
//        .setText(resourceBundle.getString("EXPORT_RULE"));
        saveProject.setText(resourceBundle.getString("SAVE_PROJECT"));
        openProject.setText(resourceBundle.getString("OPEN_PROJECT"));
        searchBtn.setText(resourceBundle.getString("SEARCH"));


        exportAll.setText(resourceBundle.getString("EXPORT_ALL"));
        /*
        *
        *
        *
        * */

        exportDataBtn.setText(resourceBundle.getString("EXPORT_BUTTON"));
        queryString.setText(resourceBundle.getString("QUERY_CONTENT"));
//        checkHoneyPot.setText(resourceBundle.getString("REMOVE_HONEYPOTS"));
//        withFid.setText(resourceBundle.getString("WITH_FID"));
//        hostOnly.setText(resourceBundle.getString("HOST_ONLY"));
        isAll.setText(resourceBundle.getString("IS_ALL"));
        decoratedField = new AutoHintTextField(queryTF);
        this.client = DataUtil.loadConfigure();
        this.tabPane.setCallback(new MainControllerCallback() {
            @Override
            public void queryCall(List<String> strList) {
                query(strList);
            }
        });
        //初始化起始页tab
        Tab tab = this.tabPane.getTab(resourceBundle.getString("HOMEPAGE"));
        Button queryCert = new Button(resourceBundle.getString("QUERY_BUTTON"));
        Button queryFavicon = new Button(resourceBundle.getString(("QUERY_BUTTON")));
        Label label = new Label(resourceBundle.getString("CERT_LABEL"));
        Label faviconLabel = new Label(resourceBundle.getString("FAVICON_LABEL"));
        TextField tf = TextFields.createClearableTextField();
        TextField favionTF = TextFields.createClearableTextField();
        // 设置从文件导入favicon
        favionTF.setOnDragOver(event -> {
            if (event.getGestureSource() != favionTF){
                event.acceptTransferModes(TransferMode.ANY);
            }
        });
        favionTF.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasFiles()){
                try {
                    File file = dragboard.getFiles().get(0);
                    if (file != null && file.exists()) {
                        favionTF.setText(file.getAbsolutePath());
                    }
                }catch (Exception e){
                    Logger.error(e.toString());
                }
            }
        });
//        Image image = new Image("api_doc_en.png");
        ImageView view = new ImageView(new Image(Locale.getDefault().getLanguage().equals(Locale.CHINESE.getLanguage()) ? "/images/ddddddddd.png" : "/images/api_doc_en.png"));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(view);
        view.setFitHeight(750D);
        view.setFitWidth(750D);
        scrollPane.setPrefWidth(760);
        tf.setPromptText(resourceBundle.getString("CERT_HINT"));
        favionTF.setPromptText(resourceBundle.getString("FAVICON_HINT"));
        tf.setPrefWidth(400);
        favionTF.setPrefWidth(400);
        label.setFont(Font.font(14));
        faviconLabel.setFont(Font.font(14));
        queryCert.setOnAction(event -> {
            String txt = tf.getText().trim();
            if(!txt.isEmpty()){
                String serialnumber = txt.replaceAll(" ", "");
                BigInteger i = new BigInteger(serialnumber, 16);
                query(new ArrayList<String>(){{add("cert=\"" + i + "\"");}});
            }
        });
        queryFavicon.setOnAction(event -> {
            String text = favionTF.getText().trim();
            if(!text.isEmpty()){
                if(!text.startsWith("http")){ // 文件导入
                    String suffix = text.substring(text.lastIndexOf(".")+1).toLowerCase();
                    try {
                        byte[] content = Files.readAllBytes(Paths.get(text));
                        switch (suffix){
                            case "jpg": case "png": case "ico": case "svg":
                                String encode = java.util.Base64.getMimeEncoder().encodeToString(content);
                                query(new ArrayList<String>(){{add("icon_hash=\"" + helper.getIconHash(encode) + "\"");}});
                                break;
                            default:
                                DataUtil.showAlert(Alert.AlertType.ERROR, null, resourceBundle.getString("ERROR_FILE")).showAndWait();
                                break;
                        }
                    } catch (IOException e) {
                        DataUtil.showAlert(Alert.AlertType.ERROR, null, resourceBundle.getString("ERROR_FILE")).showAndWait();
                        Logger.error(e);
                    }
                }else { // url导入
                    HashMap<String,String> res = helper.getImageFavicon(text);
                    if(res != null){
                        if(res.get("code").equals("error")){
                            DataUtil.showAlert(Alert.AlertType.ERROR, null, res.get("msg")).showAndWait();return;
                        }
                        query(new ArrayList<String>(){{add(res.get("msg"));}});
                    }
                }
            }
        });
        VBox vb = new VBox();
        HBox hb = new HBox();
        HBox faviconBox = new HBox();
        HBox imageBox = new HBox();
        vb.setSpacing(10);
        imageBox.getChildren().add(scrollPane);
        hb.getChildren().addAll(label, tf, queryCert);
        label.setPadding(new Insets(3));
        faviconLabel.setPadding(new Insets(3,5,3,3));
        hb.setPadding(new Insets(10));
        hb.setSpacing(15);
        faviconBox.setSpacing(15); // 设置控件间距
        hb.setAlignment(Pos.TOP_CENTER);
        faviconBox.setAlignment(Pos.TOP_CENTER);
        faviconBox.setPadding(new Insets(5,0,5,0));
        imageBox.setAlignment(Pos.TOP_CENTER);
        imageBox.setPadding(new Insets(5,0,10,0));
        faviconBox.getChildren().addAll(faviconLabel, favionTF, queryFavicon);
        vb.getChildren().addAll(hb, faviconBox, imageBox);
        tab.setContent(vb);
    }

    /**
     * 通过命令行参数传递要打开的项目文件
     */




    public void openFile(String fileName){
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> list = new ArrayList<>();
            String str;
            while((str = bufferedReader.readLine()) != null) {
                if(!str.equals("")){
                    //打印输出cccccccccccccccccccccccc
//                    System.out.println(str);
                    list.add(str);
                }
            }
            query(list);
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    @FXML
    private void getQueryAPI(){
//        Tab tab = this.tabPane.getCurrentTab();
//        if(tab.getText().equals(resourceBundle.getString("HOMEPAGE"))){
//            DataUtil.showAlert(Alert.AlertType.INFORMATION, null, resourceBundle.getString("COPY_QUERY_URL_FAILED")).showAndWait();
//        }else{
//            Clipboard clipboard = Clipboard.getSystemClipboard();
//            ClipboardContent content = new ClipboardContent();
//            content.putString(this.tabPane.getCurrentQuery(tab));
//            clipboard.setContent(content);
//            DataUtil.showAlert(Alert.AlertType.INFORMATION, null, resourceBundle.getString("COPY_QUERY_URL_SUCCESS")).showAndWait();
//        }
    }

    /**
     * 查询按钮点击事件，与fxml中命名绑定
     */
    @FXML
    private void queryAction(){
        if(queryTF.getText() != null){
            query(new ArrayList<String>(){{add(queryTF.getText());}});
        }
    }

    /**
     * 关于 按钮
     */
    @FXML
    private void showAbout(){
        List<CommandLinksDialog.CommandLinksButtonType> clb = Arrays.asList(
                new CommandLinksDialog.CommandLinksButtonType("https://thebatmanfuture.github.io",
                        resourceBundle.getString("ABOUT_HINT1"), true),
                new CommandLinksDialog.CommandLinksButtonType("https://thebatmanfuture.github.io/gitbook/",
                        resourceBundle.getString("ABOUT_HINT2"), true)
        );
        CommandLinksDialog dialog = new CommandLinksDialog(clb);
        dialog.setOnCloseRequest(e -> {
            ButtonType result = dialog.getResult();
            if(result.getButtonData() != ButtonBar.ButtonData.CANCEL_CLOSE){
                URI uri = URI.create(result.getText());
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        dp.browse(uri);
                    } catch (IOException ex) {
                        Logger.error(ex);
                    }
                }
            }
        });
        dialog.setTitle("fofa_search");
        dialog.setContentText("塔菲");
        dialog.showAndWait();
    }

    /**
     * 配置设置
     */
    @FXML
    private void setConfig(){
        SetConfiDialog dialog = new SetConfiDialog(resourceBundle.getString("CONFIG_PANEL"));
        dialog.showAndWait();
    }

    @FXML
    private void no_repeat_func() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String fileName = selectedFile.getPath();
//        System.out.println(fileName);
        try {
            // 读取原始文件
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            Set<String> uniqueLines = new HashSet<>();
            String line;

            // 去重处理
            while ((line = reader.readLine()) != null) {
                uniqueLines.add(line);
            }

            reader.close();

            // 生成去重后的文件名
            String outputFileName = fileName.replace(".txt", "[已去重].txt");

            // 写入去重后的内容到新文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            for (String uniqueLine : uniqueLines) {
                writer.write(uniqueLine);
                writer.newLine();
            }
            writer.close();

//            System.out.println("去重成功");
            showAlert(Alert.AlertType.INFORMATION, "操作提示", "去重成功");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void urlSettings(){
        SetUrlCombination combination1 = new SetUrlCombination(resourceBundle.getString("URLCSETTINGS"));
        combination1.showAndWait();
    }

    /**
     *  url存活判断
     */
    @FXML
    private void urlLive(){

    }

    @FXML
    private void urlslicefunc(){

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                String outputFolderPath = createOutputFolder();

                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    int fileIndex = 1;
                    int lineCount = 0;
                    String line;
                    BufferedWriter writer = null;

                    while ((line = reader.readLine()) != null) {
                        if (lineCount % 100 == 0) {
                            if (writer != null) {
                                writer.close();
                            }
                            String fileName = outputFolderPath + File.separator + (fileIndex * 100 - 99) + "-" + (fileIndex * 100) + ".txt";
                            writer = new BufferedWriter(new FileWriter(fileName));
                            fileIndex++;
                        }

                        writer.write(line);
                        writer.newLine();
                        lineCount++;
                    }

                    if (writer != null) {
                        writer.close();
                    }

                    showAlert(Alert.AlertType.INFORMATION, "操作完成", "已转换成功");
                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, "错误", "无法读取文件或创建输出文件");
                }
            }



    }



    @FXML
    private void awvsTrans(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String outputFolderPath = createOutputFolder();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                int fileIndex = 1;
                int lineCount = 0;
                String line;
                BufferedWriter writer = null;

                while ((line = reader.readLine()) != null) {
                    if (lineCount % 400 == 0) {
                        if (writer != null) {
                            writer.close();
                        }
                        String fileName = outputFolderPath + File.separator + (fileIndex * 400 - 399) + "-" + (fileIndex * 400) + ".csv";
                        writer = new BufferedWriter(new FileWriter(fileName));
                        fileIndex++;
                    }

                    String modifiedLine = line + "," + getCurrentDate();
                    writer.write(modifiedLine);
                    writer.newLine();
                    lineCount++;
                }

                if (writer != null) {
                    writer.close();
                }

                showAlert(Alert.AlertType.INFORMATION, "操作完成", "已转换成功");
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "错误", "无法读取文件或创建输出文件");
            }
        }
    }

    private String createOutputFolder() {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String folderName = sdf.format(date) + "-awvs";
        String folderPath = "." + File.separator + folderName;

        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                showAlert(Alert.AlertType.ERROR, "错误", "无法创建输出文件夹");
            }
        }

        return folderPath;
    }

    private String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * 打开项目
     */
    @FXML
    private void openProject(){
        if((Boolean) projectInfo.get("status")){
            Alert dialog = DataUtil.showAlert(Alert.AlertType.CONFIRMATION, null, resourceBundle.getString("OPEN_NEW_PROCESS"));
            dialog.setOnCloseRequest(event -> {
                ButtonType btn = dialog.getResult();
                if(btn.equals(ButtonType.OK)){//当前已打开一个项目点是
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle(resourceBundle.getString("FILE_CHOOSER_TITLE"));
                    File file = chooser.showOpenDialog(rootLayout.getScene().getWindow());
                    if(file != null){
                        String os = System.getProperty("os.name").toLowerCase();
                        String javaPath = System.getProperty("java.home") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator");
                        try {
                            if(os.contains("windows")){
                                String jarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().substring(1);
                                javaPath += "java.exe";
                                Runtime.getRuntime().exec(new String[]{"cmd", "/c", javaPath, "-jar", jarPath, "-f", file.getAbsolutePath()});
                            }else{
                                String jarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
                                System.out.println(jarPath);
                                javaPath += "java";
                                Runtime.getRuntime().exec(new String[]{"sh", "-c", "\"" + javaPath, "-jar", jarPath, "-f", file.getAbsolutePath(), "\""});
                            }
                        }catch (IOException e) {
                            Logger.error(e);
                        }
                    }
                }else{
                    //当前已打开一个项目点否

                }
            });
            dialog.showAndWait();
        }else{
            FileChooser chooser = new FileChooser();
            chooser.setTitle(resourceBundle.getString("FILE_CHOOSER_TITLE"));
            File file = chooser.showOpenDialog(rootLayout.getScene().getWindow());
            if(file != null){
                openFile(file.getAbsolutePath());
            }
        }
    }

    /**
     * 保存项目
     */
    @FXML
    private void saveProject(){
        if(this.tabPane.getTabs().size() == 1){
            DataUtil.showAlert(Alert.AlertType.WARNING, null, resourceBundle.getString("SAVE_PROJECT_ERROR")).showAndWait();
        }else{
            SaveOptionCallback callback = new SaveOptionCallback() {
                @Override
                public void setProjectName(String name) {
                    projectInfo.put("name", name);
                }
                @Override
                public String getProjectName() {
                    return projectInfo.get("name").toString();
                }
            };
            SaveOptionDialog sd = new SaveOptionDialog(this.tabPane, true, callback);
            sd.setOnCloseRequest(event -> {
                ButtonType rs = sd.getResult();
                if(rs.equals(ButtonType.OK)){
                    projectInfo.put("status", Boolean.TRUE);
                }
            });
            sd.showAndWait();
        }
    }

//    /**
//     * 创建规则
//     */
//    @FXML
//    private void createRule(){
//        if(this.tabPane.getTabs().size() == 1){
//            DataUtil.showAlert(Alert.AlertType.WARNING, null, resourceBundle.getString("SAVE_RULE_ERROR")).showAndWait();
//        }else{
//            SaveOptionDialog sd = new SaveOptionDialog(this.tabPane, false, null);
//            sd.showAndWait();
//        }
//    }
//
//    /**
//     * 导出规则
//     */
//    @FXML
//    private void exportRule(){
//
//    }

    @FXML
    private void importTxt() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Alert progressAlert = new Alert(AlertType.INFORMATION);
            progressAlert.setTitle("URL进度");
            progressAlert.setHeaderText("URL存活检查进行中...");
            progressAlert.initOwner(null);
//            progressAlert.initStyle(StageStyle.UTILITY);
//            progressAlert.getButtonTypes().clear();

            ProgressBar progressBar = new ProgressBar();
            progressBar.setPrefWidth(300);
//            progressAlert.getDialogPane().setContent(progressBar);

//            progressAlert.setOnCloseRequest(event -> event.consume());

            progressAlert.show();

            ExecutorService executor = Executors.newFixedThreadPool(10);
            AtomicInteger finishedCount = new AtomicInteger(0);

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                List<String> urls = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
                    urls.add(line.trim());
                }

                int totalUrls = urls.size();

                for (String url : urls) {
                    executor.execute(() -> {
                        boolean isAlive = checkUrlAlive(url);

                        if (isAlive) {
                            synchronized (selectedFile) {
                                writeUrlToFile(url, selectedFile);
                            }
                        }

                        int currentCount = finishedCount.incrementAndGet();
                        updateProgress(progressAlert, currentCount, totalUrls);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file read error
            }

            executor.shutdown();
            Platform.runLater(() -> {
                progressAlert.setContentText("URL存活已判断完成");
                progressAlert.setHeaderText(null);
                progressAlert.getButtonTypes().clear();
                progressAlert.getButtonTypes().add(ButtonType.OK);
            });
        }
    }



    private static boolean checkUrlAlive(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            if (connection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
                httpsConnection.setHostnameVerifier((hostname, session) -> true);
            }
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            } else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
                    responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                    responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String redirectUrl = connection.getHeaderField("Location");
                if (redirectUrl != null && !redirectUrl.isEmpty()) {
                    HttpURLConnection redirectConnection = (HttpURLConnection) new URL(redirectUrl).openConnection();
                    if (redirectConnection instanceof HttpsURLConnection) {
                        HttpsURLConnection httpsRedirectConnection = (HttpsURLConnection) redirectConnection;
                        httpsRedirectConnection.setHostnameVerifier((hostname, session) -> true);
                    }
                    redirectConnection.setRequestMethod("GET");
                    int redirectResponseCode = redirectConnection.getResponseCode();
                    return redirectResponseCode == HttpURLConnection.HTTP_OK;
                }
            }

            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private void writeUrlToFile(String url, File originalFile) {
        String fileName = originalFile.getName();
        String renamedFileName = fileName.replace(".txt", "[urlFinished].txt");
        File outputFile = new File(originalFile.getParent(), renamedFileName);

        try (FileWriter writer = new FileWriter(outputFile, true)) {
            writer.write(url + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file write error
        }
    }

    private void updateProgress(Alert progressAlert, int currentCount, int totalUrls) {
        Platform.runLater(() -> {
            double progress = (double) currentCount / totalUrls;
            progress *= 100;
            int percentage = (int) Math.floor(progress);
            progressAlert.setContentText(percentage + "%");

            if (currentCount == totalUrls) {
                progressAlert.close();
            }
        });
    }




    /**
     * 导出全部查询数据到txt
     */

    @FXML
    private void exportAll() {
        List<Tab> tabs = tabPane.getTabs();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(resourceBundle.getString("DIRECTORY_CHOOSER_TITLE"));
        File file = directoryChooser.showDialog(rootLayout.getScene().getWindow());
        if (file != null) {
            List<String> urlList = new ArrayList<>();

            for (Tab tab : tabs) {
                if (tab.getText().equals(resourceBundle.getString("HOMEPAGE"))) continue;  // 首页无数据可导出

                TabDataBean bean = this.tabPane.getTabDataBean(tab);
                if (!bean.hasMoreData) { // 从本地数据加载，不再进行网络请求
                    Node content = tab.getContent();

                    if (content instanceof BorderPane) {
                        BorderPane borderPane = (BorderPane) content;
                        Node centerNode = borderPane.getCenter();

                        if (centerNode instanceof TableView) {
                            TableView<TableBean> tableView = (TableView<TableBean>) centerNode;
//                    TableView<TableBean> tableView = (TableView<TableBean>) ((BorderPane) tab.getContent()).getCenter();
//                    System.out.println(tab.getContent());
//                    System.out.println(tableView.getItems());
                            for (TableBean i : tableView.getItems()) {
                                String url = i.host.getValue();  // 根据实际情况获取URL
                                String proto = i.protocol.getValue();

                                if (proto.equalsIgnoreCase("http")) {
                                    url = "http://" + url;
                                } else if (proto.equalsIgnoreCase("https")) {
                                    // 如果是 https，url 不做修改
                                } else {
                                    continue;  // 不符合要求的 proto，跳过当前循环
                                }

                                urlList.add(url);
                            }
                        }
                    }

                }else {
//                    int maxCount = Math.min(bean.total, client.max);
//                    System.out.println(maxCount);
//                    System.out.println();
//                    System.out.println(queryTF.getText());
//                  bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
//                    System.out.println((TableView<TableBean>) ((BorderPane) tab.getContent()).getCenter());
//                    System.out.println(tab.getContent());


//                    TableView<TableBean> tableView = (TableView<TableBean>) ((BorderPane) tab.getContent()).getCenter();
                    Node content = tab.getContent();

                    if (content instanceof BorderPane) {
                        BorderPane borderPane = (BorderPane) content;
                        Node centerNode = borderPane.getCenter();

                        if (centerNode instanceof TableView) {
                            TableView<TableBean> tableView = (TableView<TableBean>) centerNode;
                            for (TableBean i : tableView.getItems()) {
                                String url = i.host.getValue();  // 根据实际情况获取URL
                                String proto = i.protocol.getValue();

                                if (proto.equalsIgnoreCase("http")) {
                                    url = "http://" + url;
                                } else if (proto.equalsIgnoreCase("https")) {
                                    // 如果是 https，url 不做修改
                                } else {
                                    continue;  // 不符合要求的 proto，跳过当前循环
                                }

                                urlList.add(url);
                            }
                        }
                    }
                }

            }

            Set<String> uniqueUrls = new HashSet<>(urlList);  // 去除重复URL

            StringBuilder urlContent = new StringBuilder();
            for (String url : uniqueUrls) {
                urlContent.append(url).append("\n");
            }

            // 获取当前日期和时间
            LocalDateTime now = LocalDateTime.now();

            // 定义日期时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

            // 格式化日期和时间
            String formattedDateTime = now.format(formatter);

            // 构造文件名
            String fileName = file.getAbsolutePath() +
                    System.getProperty("file.separator") +
                    resourceBundle.getString("EXPORT_FILENAME") +
                    formattedDateTime + ".txt";



            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(urlContent.toString());
            } catch (IOException e) {
                // 处理 I/O 异常
            }

            // 弹出"全部导出成功"消息框
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("全部导出成功");
            alert.showAndWait();
        }
    }






    /**
     * 导出成AWVS格式
     */
    @FXML

    private void exportAction() {
        List<Tab> tabs = tabPane.getTabs();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(resourceBundle.getString("DIRECTORY_CHOOSER_TITLE"));
        File file = directoryChooser.showDialog(rootLayout.getScene().getWindow());
        if (file != null) {
            List<String> urlList = new ArrayList<>();

            for (Tab tab : tabs) {
                if (tab.getText().equals(resourceBundle.getString("HOMEPAGE"))) continue;  // 首页无数据可导出

                TabDataBean bean = this.tabPane.getTabDataBean(tab);
                if (!bean.hasMoreData) { // 从本地数据加载，不再进行网络请求
                    Node content = tab.getContent();

                    if (content instanceof BorderPane) {
                        BorderPane borderPane = (BorderPane) content;
                        Node centerNode = borderPane.getCenter();

                        if (centerNode instanceof TableView) {
                            TableView<TableBean> tableView = (TableView<TableBean>) centerNode;
//                    TableView<TableBean> tableView = (TableView<TableBean>) ((BorderPane) tab.getContent()).getCenter();
                            for (TableBean i : tableView.getItems()) {
                                String url = i.host.getValue();  // 根据实际情况获取URL
                                String proto = i.protocol.getValue();

                                if (proto.equalsIgnoreCase("http")) {
                                    url = "http://" + url;
                                } else if (proto.equalsIgnoreCase("https")) {
                                    // 如果是 https，url 不做修改
                                } else {
                                    continue;  // 不符合要求的 proto，跳过当前循环
                                }

                                urlList.add(url);
                            }
                        }
                    }

                }else{
                    Node content = tab.getContent();

                    if (content instanceof BorderPane) {
                        BorderPane borderPane = (BorderPane) content;
                        Node centerNode = borderPane.getCenter();

                        if (centerNode instanceof TableView) {
                            TableView<TableBean> tableView = (TableView<TableBean>) centerNode;
//                    TableView<TableBean> tableView = (TableView<TableBean>) ((BorderPane) tab.getContent()).getCenter();
                            for (TableBean i : tableView.getItems()) {
                                String url = i.host.getValue();  // 根据实际情况获取URL
                                String proto = i.protocol.getValue();

                                if (proto.equalsIgnoreCase("http")) {
                                    url = "http://" + url;
                                } else if (proto.equalsIgnoreCase("https")) {
                                    // 如果是 https，url 不做修改
                                } else {
                                    continue;  // 不符合要求的 proto，跳过当前循环
                                }

                                urlList.add(url);
                            }
                        }
                    }

                }
            }

            Set<String> uniqueUrls = new HashSet<>(urlList);  // 去除重复URL

            // 获取当前日期和时间
            LocalDateTime now = LocalDateTime.now();

            // 定义日期时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // 格式化日期
            String formattedDate = now.format(formatter);

            // 创建文件夹路径
            String folderPath = file.getAbsolutePath() + System.getProperty("file.separator") + formattedDate;
            File folder = new File(folderPath);

            if (!folder.exists()) {
                folder.mkdirs(); // 创建文件夹
            }

            int count = 1;
            int batchSize = 400;

            List<String> batchUrls = new ArrayList<>();

            for (String url : uniqueUrls) {
                batchUrls.add(url);

                if (batchUrls.size() == batchSize || count == uniqueUrls.size()) {
                    // 构造文件名
                    String fileName = folderPath + System.getProperty("file.separator") +
                            formattedDate + "-" + ((count - 1) - batchSize + 1) + "_" + (count - 1) + ".csv";

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (String batchUrl : batchUrls) {
                            String urlWithDate = batchUrl + "," + formattedDate;
                            writer.write(urlWithDate);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        // 处理 I/O 异常
                    }

                    batchUrls.clear();
                }

                count++;
            }


            // 弹出"全部导出成功"消息框
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("全部导出成功");
            alert.showAndWait();
        }
    }




    /**
     * 处理查询结果
     */
    public void query(List<String> strList){
        ArrayList<RequestBean> beans = new ArrayList<>();
        for(String text:strList) {

            String tabTitle = text.trim();
            if (text.startsWith("(*)")) {
                tabTitle = text;
                text = text.substring(3);
                text = "(" + text + ") && (is_honeypot=false && is_fraud=false)";
            }
//            if (checkHoneyPot.isSelected() && !text.contains("(is_honeypot=false && is_fraud=false)")) {
//                tabTitle = "(*)" + text;
//                text = "(" + text + ") && (is_honeypot=false && is_fraud=false)";
//            }
            final String queryText = text;
            // dddddddddddddddddddddddddddddddd
//            System.out.println(queryText);
            if (this.tabPane.isExistTab(tabTitle)) { // 若已存在同名Tab 则直接跳转，不查询
                if(strList.size() == 1){
                    this.tabPane.setCurrentTab(this.tabPane.getTab(tabTitle));
                    return;
                }else{
                    continue;
                }
            }
            for (CheckBox box : keyMap.keySet()) {
                String name = keyMap.get(box);
                if (box.isSelected()) {
                    if (!client.fields.contains(name)) {
                        client.fields.add(name);
                    }
                } else {
                    client.fields.remove(name);
                }
            }
            Tab tab = new Tab();
            tab.setOnCloseRequest(event -> tabPane.closeTab(tab));
            tab.setText(tabTitle);
            tab.setTooltip(new Tooltip(tabTitle));
            String url = client.getParam(null, isAll.isSelected()) + helper.encode(queryText);
//            System.out.println(url);
            RequestBean bean = new RequestBean(url, tabTitle, client.getSize());
            bean.setTab(tab);
            beans.add(bean);
        }
        MainControllerCallback mCallback = new MainControllerCallback() {
//            @Override
//            public boolean getFidStatus() {
//                return withFid.isSelected();
//            }

            @Override
            public void queryCall(List<String> strList) {
                query(strList);
            }

            @Override
            public void addSBListener(TableView<?> view) {
                addScrollBarListener(view);


            }
        };

        new Request(beans, new RequestCallback<Request>() {
            @Override
            public void before(TabDataBean tabDataBean, RequestBean bean) {
                tabPane.addTab(bean.getTab(), tabDataBean, bean.getRequestUrl());
                tabPane.setCurrentTab(bean.getTab());
                LoadingPane ld = new LoadingPane();
                bean.getTab().setContent(ld);
            }

            @Override
            public void succeeded(BorderPane tablePane, StatusBar bar, RequestBean bean) {
                if (bean.getResult().get("code").equals("200")) {
//fffffffffffffffffffffffff
                    String result = String.valueOf(bean.getResult());

                    // 提取返回包中的"size"对应的值
                    String sizeValue = "";
                    int sizeIndex = result.indexOf("\"size\":");
                    if (sizeIndex != -1) {
                        int valueStartIndex = sizeIndex + "\"size\":".length();
                        int valueEndIndex = result.indexOf(",", valueStartIndex);
                        if (valueEndIndex == -1) {
                            valueEndIndex = result.length() - 1;
                        }
                        sizeValue = result.substring(valueStartIndex, valueEndIndex).trim();
                    }

//                    System.out.println("Size value: " + sizeValue);

                    // 判断 sizeValue 是否大于 client.max
                    if (!sizeValue.isEmpty()) {
                        int maxSize = Integer.parseInt(sizeValue);
                        if (maxSize > client.max) {
                            // 创建文件夹
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                            String folderName = dateFormat.format(new Date());
                            File folder = new File(folderName);
                            if (!folder.exists()) {
                                folder.mkdirs();
                            }

                            // 创建并追加写入文件
                            SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String fileName = fileDateFormat.format(new Date()) + "[超过最大限制语句].txt";
                            File file = new File(folder, fileName);
                            try (FileWriter writer = new FileWriter(file, true)) {
                                // 解码 qbase64 参数值
                                String decodedValue = "";
                                int qbase64Index = bean.getRequestUrl().indexOf("qbase64=");
                                if (qbase64Index != -1) {
                                    int valueStartIndex = qbase64Index + "qbase64=".length();
                                    int valueEndIndex = bean.getRequestUrl().indexOf("&", valueStartIndex);
                                    if (valueEndIndex == -1) {
                                        valueEndIndex = bean.getRequestUrl().length();
                                    }
                                    String encodedValue = bean.getRequestUrl().substring(valueStartIndex, valueEndIndex);
                                    decodedValue = new String(Base64.getDecoder().decode(encodedValue), StandardCharsets.UTF_8);
                                }

                                // 写入解码后的值到文件
                                writer.write(decodedValue);
                                writer.write(System.lineSeparator());  // 换行
                            } catch (IOException e) {
                                e.printStackTrace();
                                // 处理文件写入异常
                            }
                        }
                    }






                    bean.getTab().setContent(tablePane);
                    decoratedField.addLog(bean.getTabTitle());
                    tabPane.addBar(bean.getTab(), bar);
                } else {
                    // eeeeeeeeeeeeeeeeeeee
                    ((LoadingPane)bean.getTab().getContent()).setErrorText("请求状态码："+bean.getResult().get("code")+ bean.getResult().get("msg"));
                }
            }

            @Override
            public void failed(String text, RequestBean bean) { // 网络问题请求失败
                ((LoadingPane) bean.getTab().getContent()).setErrorText(text);
            }
        }, mCallback).query(beans.size());
    }

    /**
     * 设置滚动自动加载，需要等tableview加载完后设置
     * @param view tableview
     */
    private void addScrollBarListener(TableView<?> view){
        ScrollBar bar = (ScrollBar) view.lookup(".scroll-bar:vertical");
        bar.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ((double) newValue == 1.0D) {
                Tab tab = tabPane.getCurrentTab();
                TabDataBean bean = tabPane.getTabDataBean(tab);
                if (bean.hasMoreData) {
                    bean.page += 1;
                    String text = DataUtil.replaceString(tab.getText());
                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() {
                            HashMap<String, String> result = helper.getHTML(client.getParam(String.valueOf(bean.page),
                                    isAll.isSelected()) + helper.encode(text), 1200000, 1200000);
                            Node content = tab.getContent();

                            if (content instanceof BorderPane) {
                                BorderPane borderPane = (BorderPane) content;
                                Node centerNode = borderPane.getCenter();

                                if (centerNode instanceof TableView) {
                                    TableView<TableBean> tableView = (TableView<TableBean>) centerNode;
//                            TableView<TableBean> tableView = (TableView<TableBean>) ((BorderPane) tab.getContent()).getCenter();
                                    if (result.get("code").equals("200")) {
                                        JSONObject obj = JSON.parseObject(result.get("msg"));
                                        if (obj.getBoolean("error")) {
                                            return null;
                                        }
                                        List<TableBean> list = (List<TableBean>) DataUtil.loadJsonData(bean, obj, null, null, false);
                                        if (list.size() != 0) {
                                            List<TableBean> tmp = list.stream().sorted(Comparator.comparing(TableBean::getIntNum)).collect(Collectors.toList());
                                            Platform.runLater(() -> tableView.getItems().addAll(FXCollections.observableArrayList(tmp)));
                                            Platform.runLater(() -> tableView.scrollTo(tableView.getItems().size() - Integer.parseInt(client.getSize())));
                                            StatusBar statusBar = tabPane.getBar(tab);
                                            Label countLabel = (Label) statusBar.getRightItems().get(1);
                                            Platform.runLater(() -> countLabel.setText(String.valueOf(Integer.parseInt(countLabel.getText()) + obj.getJSONArray("results").size())));
                                            if (client.getCheckStatus()) {
                                                result = helper.getLeftAmount(String.format(client.personalInfoAPI, client.getEmail(), client.getKey()), 120000, 120000);
                                                if (result.get("code").equals("200")) {
                                                    Label infoLabel = (Label) statusBar.getLeftItems().get(0);
                                                    String msg = result.get("msg");
                                                    Platform.runLater(() -> {
                                                        statusBar.setText("");
                                                        infoLabel.setText(msg);
                                                    });
                                                }
                                            }
                                        }
                                        if (bean.page * Integer.parseInt(client.getSize()) > obj.getInteger("size")) {
                                            bean.hasMoreData = false;
                                        }
                                    }
                                }
                            }
                            return null;
                        }
                    };
                    new Thread(task).start();
                }
            }
        });
    }
}
