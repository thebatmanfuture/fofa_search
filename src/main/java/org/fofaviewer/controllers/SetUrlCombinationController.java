package org.fofaviewer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import org.controlsfx.control.StatusBar;
import org.fofaviewer.bean.RequestBean;
import org.fofaviewer.bean.TabDataBean;
import org.fofaviewer.callback.MainControllerCallback;
import org.fofaviewer.callback.RequestCallback;
import org.fofaviewer.controls.LoadingPane;
import org.fofaviewer.main.FofaConfig;
import org.fofaviewer.request.Request;
import org.fofaviewer.utils.ResourceBundleUtil;

import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class SetUrlCombinationController {

    private DialogPane dialogPane;

    private ResourceBundle bundle;

    Properties properties = new Properties();

    private FofaConfig client;

    private String percentageLabel_result;

    public void setAction(DialogPane dialogPane) {
        this.dialogPane = dialogPane;
    }


    @FXML
    private Label urlFileLineCountLabel;

    private int fofa_size_int;


    @FXML
    private Label fofa_size;

    @FXML
    private Button importFofaApiFile;

    @FXML
    private Label fofaApiFilePathLabel;

    @FXML
    private Label label_fofa_api;

    @FXML
    private Label import_file_name;

    @FXML
    private Label import_file_name_comb;

    @FXML
    private Button importFofaEmailFile;

    @FXML
    private Label fofaEmailFilePathLabel;

    @FXML
    private CheckBox webProtocolCheckbox;

    @FXML
    private CheckBox mainlandRegionCheckbox;

    @FXML
    private CheckBox forbiddenHashCheckbox;

    @FXML
    private CheckBox routerFirewallCheckbox;

    @FXML
    private Button generateButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button detectionButton;

    @FXML
    private TextField initialSyntaxField;

    @FXML
    private Button importUrlFileButton;

    @FXML
    private Label percentageLabel;



    @FXML
    private void initialize(){
        bundle = ResourceBundleUtil.getResource();
        import_file_name.setText(bundle.getString("IMPORTFILE_BASE"));
        import_file_name_comb.setText(bundle.getString("IMPORTFILE_COMB"));
    }


    @FXML
    private void importFofaApiFile() {
        // 选择导入基础语法文件
        File selectedFile = chooseFile("选择基础语法文件");
        if (selectedFile != null) {
            // 处理导入的基础语法文件
            handleImportedFile(selectedFile, fofaApiFilePathLabel);
        }
    }

    @FXML
    private void importFofaEmailFile() {
        // 选择导入组合语法文件
        File selectedFile = chooseFile("选择组合语法文件");
        if (selectedFile != null) {
            // 处理导入的组合语法文件
            handleImportedFile(selectedFile, fofaEmailFilePathLabel);
        }
    }

    @FXML
    private void generateCombination() {
        // 获取复选框的选择状态
        boolean includeWebProtocol = webProtocolCheckbox.isSelected();
        boolean includeMainlandRegion = mainlandRegionCheckbox.isSelected();
        boolean includeForbiddenHash = forbiddenHashCheckbox.isSelected();
        boolean includeRouterFirewall = routerFirewallCheckbox.isSelected();

        // 获取导入的基础语法和组合语法
        String apiSyntax = fofaApiFilePathLabel.getText();
        String emailSyntax = fofaEmailFilePathLabel.getText();

        if (apiSyntax.isEmpty() || emailSyntax.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "错误", "请先导入基础语法和组合语法文件");
            return;
        }

        try {
            // 读取基础语法和组合语法文件内容
            List<String> apiSyntaxLines = Files.readAllLines(Paths.get(apiSyntax), StandardCharsets.UTF_8);
            List<String> emailSyntaxLines = Files.readAllLines(Paths.get(emailSyntax), StandardCharsets.UTF_8);

            // 构建生成的组合语法列表
            List<String> combinationSyntaxList = new ArrayList<>();

            for (String apiLine : apiSyntaxLines) {
                for (String emailLine : emailSyntaxLines) {
                    StringBuilder combinationSyntax = new StringBuilder(apiLine);
                    combinationSyntax.append(" && ").append(emailLine);

                    if (includeWebProtocol) {
                        combinationSyntax.append(" && (protocol=\"http\" || protocol=\"https\" || protocol=\"\")");
                    }

                    if (includeMainlandRegion) {
                        combinationSyntax.append(" && (country=\"CN\" && region!=\"HK\" && region!=\"TW\" && region!=\"MO\")");
                    }

                    if (includeForbiddenHash) {
                        combinationSyntax.append(" && (icon_hash!=\"-47597126\" && icon_hash!=\"-988684079\" && icon_hash!=\"2080922081\" && icon_hash!=\"123821839\" && icon_hash!=\"-1926484046\" && icon_hash!=\"1578525679\" && icon_hash!=\"-1369819050\" && icon_hash!=\"-1046333563\" && icon_hash!=\"-2082130213\" && icon_hash!=\"-693794744\" && icon_hash!=\"-530000105\" && icon_hash!=\"366138570\" && icon_hash!=\"-1177434085\" && icon_hash!=\"1846604102\" && icon_hash!=\"857917788\" && icon_hash!=\"924635309\" && icon_hash!=\"1064516042\" && icon_hash!=\"-1797623855\" && icon_hash!=\"1670920207\" && icon_hash!=\"-1877503659\" && icon_hash!=\"-1803249142\" && icon_hash!=\"598653981\" && icon_hash!=\"353630787\" && icon_hash!=\"1090617015\" && icon_hash!=\"1322406166\" && icon_hash!=\"322071460\" && icon_hash!=\"60916281\" && icon_hash!=\"200967977\" && icon_hash!=\"2585056\" && icon_hash!=\"-587375551\" && icon_hash!=\"-886384567\" && icon_hash!=\"-759108386\" && icon_hash!=\"-1566499661\" && icon_hash!=\"1001741987\" && icon_hash!=\"772658742\" && icon_hash!=\"355730450\" && icon_hash!=\"1845536465\" && icon_hash!=\"2112438025\" && icon_hash!=\"48755205\" && icon_hash!=\"-1214117000\" && icon_hash!=\"1357981497\" && icon_hash!=\"-1792712253\" && icon_hash!=\"-587271808\" && icon_hash!=\"-898855313\" && icon_hash!=\"-160487248\" && icon_hash!=\"-1812255781\" && icon_hash!=\"-322048297\" && icon_hash!=\"-386189083\" && icon_hash!=\"-1036656423\" && icon_hash!=\"-1208073718\" && icon_hash!=\"2080922081\" && icon_hash!=\"245932412\")");
                    }

                    if (includeRouterFirewall) {
                        combinationSyntax.append(" && (title!=\"Gateway\" && title!=\"WLAN\" && title!=\"统一身份\" && title!=\"webvpn\" && title!=\"vpn\" && product!=\"Jenkins\" && product!=\"SANGFOR-SSL-VPN\" && product!=\"UDESK\" && server!=\"Sangine\" && server!=\"Switch\" && server!=\"TopWebServer\" && server!=\"See Other\")");
                    }

                    combinationSyntaxList.add(combinationSyntax.toString());
                }
            }

            // 创建文件夹路径
            String folderPath = createCombinationFolderPath();

            // 生成当前日期作为文件名前缀
            String fileNamePrefix = LocalDate.now().toString();

            // 生成组合语法文件路径
            String combinationFilePath = folderPath + File.separator + fileNamePrefix + "-[组合语法生成].txt";

            // 写入组合语法到文件中
            Files.write(Paths.get(combinationFilePath), combinationSyntaxList, StandardCharsets.UTF_8);

            showAlert(Alert.AlertType.INFORMATION, "成功", "生成组合语法文件成功！");

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "错误", "无法读取或写入文件");
        }
    }

    @FXML
    private void cancel() {
        // 关闭窗口或返回上一步操作
    }

    private File chooseFile(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(webProtocolCheckbox.getScene().getWindow());
    }
    private void handleImportedFile(File selectedFile, Label label) {try {
        // 获取文件路径
        String filePath = selectedFile.getPath();

        // 更新标签显示导入的文件路径
        label.setText(filePath);

        showAlert(Alert.AlertType.INFORMATION, "成功", "导入文件成功！");

    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "错误", "无法导入文件");
    }
    }

    private String createCombinationFolderPath() {
        String folderName = LocalDate.now().toString() + "-comb";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder.getAbsolutePath();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


//    performDetection      importUrlFile
    @FXML
    private void performDetection() {

        if(initialSyntaxField.getText() != null){
            query(new ArrayList<String>(){{add(initialSyntaxField.getText());}});
        }

    }

    @FXML
    private void importUrlFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                int lineCount = 0;
                while (reader.readLine() != null) {
                    lineCount++;
                }
                reader.close();

                // 将文件行数展示在 urlFileLineCountLabel 标签上
                urlFileLineCountLabel.setText(String.valueOf(lineCount));

                double percentage = (double) lineCount / fofa_size_int * 100;
                String percentageResult = String.format("%.2f%%", percentage);
                percentageLabel.setText(String.valueOf(percentageResult));



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 关闭窗口或返回上一步操作

        // ... 其他代码 ...
    }


    public void query(List<String> strList){
        ArrayList<RequestBean> beans = new ArrayList<>();
        for(String text:strList) {
            String tabTitle = text.trim();
            if (text.startsWith("(*)")) {
                tabTitle = text;
                text = text.substring(3);
                text = "(" + text + ") && (is_honeypot=false && is_fraud=false)";
            }

            final String queryText = text;

//            System.out.println(queryText);
            String encodedText = Base64.getEncoder().encodeToString(queryText.getBytes());
//            System.out.println(encodedText);

            try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
                properties.load(fileInputStream);

                String email = properties.getProperty("email");
                String key = properties.getProperty("key");
//                System.out.println(email);
//                System.out.println(key);

                String url = "https://fofa.info/api/v1/search/all?email=" + email + "&key=" + key + "&page=1&size=1&fields=0&qbase64=" + encodedText;
//                System.out.println(url);

                // 发送 GET 请求
                URL apiUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    String result = response.toString();

                    // 提取 "size" 对应的值
                    String sizeValue = "";
                    int sizeIndex = result.indexOf("\"size\":");
                    if (sizeIndex != -1) {
                        int valueStartIndex = sizeIndex + "\"size\":".length();
                        int valueEndIndex = result.indexOf(",", valueStartIndex);
                        if (valueEndIndex == -1) {
                            valueEndIndex = result.length() - 1;
                        }
                        sizeValue = result.substring(valueStartIndex, valueEndIndex).trim();
                        fofa_size_int = Integer.parseInt(sizeValue);
//                        fofa_size
                        fofa_size.setText(sizeValue);

                    }

//                    System.out.println("Size Value: " + sizeValue);
                } else {
//                    System.out.println("Error: " + responseCode);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }




        }
    ;}

}