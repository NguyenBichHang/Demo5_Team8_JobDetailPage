package utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotManager {
    public static void addDownloadableScreenshot(ExtentTest test, WebDriver driver, String testName) {
        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "Screenshot_" + testName + "_" + timestamp + ".png";

            // Tạo download section
            String downloadSection = createDownloadSection(base64Screenshot, fileName, testName);
            test.log(Status.INFO, downloadSection);

            // Optionally save physical file
            savePhysicalFile(driver, fileName);

        } catch (Exception e) {
            test.log(Status.WARNING, "Failed to capture downloadable screenshot: " + e.getMessage());
        }
    }
    private static String createDownloadSection(String base64Screenshot, String fileName, String testName) {
        String uniqueId = fileName.replaceAll("[^a-zA-Z0-9]", "");

        return "<div class='screenshot-download-section'>" +
                "<style>" +
                ".screenshot-download-section { border: 1px solid #007bff; border-radius: 8px; padding: 15px; margin: 10px 0; background: #f8f9fa; }" +
                ".download-btn { color: white; border: none; padding: 10px 16px; border-radius: 4px; cursor: pointer; margin: 5px; font-size: 14px; transition: all 0.3s; }" +
                ".download-btn:hover { transform: translateY(-1px); box-shadow: 0 2px 4px rgba(0,0,0,0.2); }" +
                ".btn-download { background: #007bff; } .btn-download:hover { background: #0056b3; }" +
                ".btn-clipboard { background: #6c757d; } .btn-clipboard:hover { background: #545b62; }" +
                ".btn-newtab { background: #28a745; } .btn-newtab:hover { background: #1e7e34; }" +
                ".btn-fullscreen { background: #fd7e14; } .btn-fullscreen:hover { background: #e8650e; }" +
                ".screenshot-img { max-width: 100%; height: auto; border: 1px solid #ddd; border-radius: 4px; margin-top: 10px; cursor: pointer; }" +
                ".success-msg { color: #28a745; font-size: 12px; margin-left: 10px; opacity: 0; transition: opacity 0.3s; }" +
                "</style>" +
                "<div>" +
                "<h5 style='color: #007bff; margin: 0 0 10px 0;'>📸 Screenshot: " + testName + "</h5>" +
                "<div style='margin-bottom: 10px;'>" +
                "<button class='download-btn btn-download' onclick='downloadImg_" + uniqueId + "()'>📥 Download PNG</button>" +
                "<button class='download-btn btn-clipboard' onclick='copyToClipboard_" + uniqueId + "()'>📋 Copy to Clipboard</button>" +
                "<button class='download-btn btn-newtab' onclick='openInNewTab_" + uniqueId + "()'>🔗 Open in New Tab</button>" +
//                "<button class='download-btn btn-fullscreen' onclick='toggleFullscreen_" + uniqueId + "()'>🔍 Toggle Fullscreen</button>" +
                "<span id='success-msg-" + uniqueId + "' class='success-msg'>✓ Action completed!</span>" +
                "</div>" +
                "</div>" +
                "<img id='screenshot-img-" + uniqueId + "' class='screenshot-img' src='data:image/png;base64," + base64Screenshot + "' onclick='toggleFullscreen_" + uniqueId + "()'/>" +
                "<script>" +
                "function downloadImg_" + uniqueId + "() {" +
                "  var link = document.createElement('a');" +
                "  link.download = '" + fileName + "';" +
                "  link.href = 'data:image/png;base64," + base64Screenshot + "';" +
                "  document.body.appendChild(link);" +
                "  link.click();" +
                "  document.body.removeChild(link);" +
                "  showSuccessMessage_" + uniqueId + "('Downloaded: " + fileName + "');" +
                "}" +
                "function copyToClipboard_" + uniqueId + "() {" +
                "  try {" +
                "    var img = document.getElementById('screenshot-img-" + uniqueId + "');" +
                "    var canvas = document.createElement('canvas');" +
                "    var ctx = canvas.getContext('2d');" +
                "    canvas.width = img.naturalWidth || img.width;" +
                "    canvas.height = img.naturalHeight || img.height;" +
                "    ctx.drawImage(img, 0, 0);" +
                "    canvas.toBlob(function(blob) {" +
                "      if (navigator.clipboard && navigator.clipboard.write) {" +
                "        navigator.clipboard.write([new ClipboardItem({'image/png': blob})])" +
                "        .then(function() { showSuccessMessage_" + uniqueId + "('Copied to clipboard!'); })" +
                "        .catch(function() { fallbackCopyMethod_" + uniqueId + "(); });" +
                "      } else { fallbackCopyMethod_" + uniqueId + "(); }" +
                "    }, 'image/png');" +
                "  } catch(e) { fallbackCopyMethod_" + uniqueId + "(); }" +
                "}" +
                "function fallbackCopyMethod_" + uniqueId + "() {" +
                "  var textArea = document.createElement('textarea');" +
                "  textArea.value = 'data:image/png;base64," + base64Screenshot + "';" +
                "  document.body.appendChild(textArea);" +
                "  textArea.select();" +
                "  document.execCommand('copy');" +
                "  document.body.removeChild(textArea);" +
                "  showSuccessMessage_" + uniqueId + "('Base64 copied to clipboard!');" +
                "}" +
                "function openInNewTab_" + uniqueId + "() {" +
                "  var win = window.open();" +
                "  win.document.write('<html><head><title>Screenshot - " + testName + "</title></head><body style=\"margin:0;display:flex;justify-content:center;align-items:center;min-height:100vh;background:#f0f0f0;\"><img src=\"data:image/png;base64," + base64Screenshot + "\" style=\"max-width:100%;max-height:100%;border:1px solid #ddd;\"/></body></html>');" +
                "  showSuccessMessage_" + uniqueId + "('Opened in new tab!');" +
                "}" +
//                "function toggleFullscreen_" + uniqueId + "() {" +
//                "  var img = document.getElementById('screenshot-img-" + uniqueId + "');" +
//                "  if (img.style.position === 'fixed') {" +
//                "    img.style.position = 'static';" +
//                "    img.style.top = 'auto';" +
//                "    img.style.left = 'auto';" +
//                "    img.style.transform = 'none';" +
//                "    img.style.zIndex = 'auto';" +
//                "    img.style.maxWidth = '100%';" +
//                "    img.style.maxHeight = 'auto';" +
//                "    img.style.backgroundColor = 'transparent';" +
//                "    img.style.padding = '0';" +
//                "    document.body.style.overflow = 'auto';" +
//                "  } else {" +
//                "    img.style.position = 'fixed';" +
//                "    img.style.top = '50%';" +
//                "    img.style.left = '50%';" +
//                "    img.style.transform = 'translate(-50%, -50%)';" +
//                "    img.style.zIndex = '9999';" +
//                "    img.style.maxWidth = '90vw';" +
//                "    img.style.maxHeight = '90vh';" +
//                "    img.style.backgroundColor = 'rgba(0,0,0,0.9)';" +
//                "    img.style.padding = '20px';" +
//                "    img.style.borderRadius = '8px';" +
//                "    document.body.style.overflow = 'hidden';" +
//                "  }" +
//                "}" +
                "function showSuccessMessage_" + uniqueId + "(message) {" +
                "  var msgElement = document.getElementById('success-msg-" + uniqueId + "');" +
                "  msgElement.textContent = '✓ ' + message;" +
                "  msgElement.style.opacity = '1';" +
                "  setTimeout(function() { msgElement.style.opacity = '0'; }, 3000);" +
                "}" +
                "document.addEventListener('keydown', function(e) {" +
                "  if (e.key === 'Escape') {" +
                "    var img = document.getElementById('screenshot-img-" + uniqueId + "');" +
                "    if (img.style.position === 'fixed') {" +
                "      toggleFullscreen_" + uniqueId + "();" +
                "    }" +
                "  }" +
                "});" +
                "</script>" +
                "</div>";
    }

//    private static String createDownloadSection(String base64Screenshot, String fileName, String testName) {
//        return "<div class='screenshot-download-section'>" +
//                "<style>" +
//                ".screenshot-download-section { border: 1px solid #007bff; border-radius: 8px; padding: 15px; margin: 10px 0; background: #f8f9fa; }" +
//                ".download-btn { background: #007bff; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer; margin: 5px; }" +
//                ".download-btn:hover { background: #0056b3; }" +
//                ".screenshot-img { max-width: 100%; height: auto; border: 1px solid #ddd; border-radius: 4px; margin-top: 10px; }" +
//                "</style>" +
//                "<div>" +
//                "<h5 style='color: #007bff; margin: 0 0 10px 0;'>📸 Screenshot: " + testName + "</h5>" +
//                "<button class='download-btn' onclick='downloadImg_" + fileName.replaceAll("[^a-zA-Z0-9]", "") + "()'>📥 Download</button>" +
//                "<button class='download-btn' onclick='openInNewTab_" + fileName.replaceAll("[^a-zA-Z0-9]", "") + "()' style='background: #28a745;'>🔗 Open in New Tab</button>" +
//                "</div>" +
//                "<img class='screenshot-img' src='data:image/png;base64," + base64Screenshot + "'/>" +
//                "<script>" +
//                "function downloadImg_" + fileName.replaceAll("[^a-zA-Z0-9]", "") + "() {" +
//                "  var link = document.createElement('a');" +
//                "  link.download = '" + fileName + "';" +
//                "  link.href = 'data:image/png;base64," + base64Screenshot + "';" +
//                "  link.click();" +
//                "}" +
//                "function openInNewTab_" + fileName.replaceAll("[^a-zA-Z0-9]", "") + "() {" +
//                "  var win = window.open();" +
//                "  win.document.write('<img src=\"data:image/png;base64," + base64Screenshot + "\" style=\"max-width:100%;height:auto;\"/>');" +
//                "}" +
//                "</script>" +
//                "</div>";
//    }

    private static void savePhysicalFile(WebDriver driver, String fileName) {
        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(sourceFile, destFile);

        } catch (Exception e) {
            System.out.println("Failed to save physical screenshot file: " + e.getMessage());
        }
    }
}
