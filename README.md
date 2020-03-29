# CodeDemo
For interview and personal study

功能:
* RecycleView
* API 串接
* 資料解析
* 資料傳遞
* 本地儲存

第一頁
---------------
* 利用[中央氣象局開放資料](https://opendata.cwb.gov.tw/dist/opendata-swagger.html#/%E9%A0%90%E5%A0%B1/get_v1_rest_datastore_F_C0032_001) 
  選取未來36小時&台北市資料
* 用recycleView實作
  每個欄位高200dp
  取出臺北市MinT欄位的氣溫資料 
  顯示於中央
* 並且將recycleView分為兩種type
  一種是資料 一種是圖片
  在每筆縣市資料(type A)之間 插入一張任意圖片(type B)
* 點擊type A時跳至下一頁 並將該筆資料全部帶到下一頁
  點擊type B則不須反應

第二頁
-----------------
* 用ConstraintLayout實作如下圖ui畫面. 
  註:A與B區塊高度寬度任意, 比例為1:1, 背景顏色任意
* 依照螢幕高度分成上下兩半, 將點擊欄位的資料 (startTime endTime parameterName parameterUnit ) 
  依序排列於 下半部頂端的水平置中位置
* 關閉App後 第二次開啟時
  顯示”歡迎回來”字樣的dialog或toast
  
![Sample_Screen](/sample_screen.jpg)
