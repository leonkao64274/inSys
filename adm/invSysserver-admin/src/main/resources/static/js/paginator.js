 	//分頁，頁碼導航,要求參數為一個對象
function createPageNav(opt) {
    opt= opt || {};
    var $container   = opt.$container          || null, //必需，頁碼容器，來確保這個容器只用來存放頁碼導航
        pageCount    = Number(opt.pageCount)   || 0,    //必需，頁碼總數
        currentNum   = Number(opt.currentNum)  || 1,    // 選填，當前頁碼
        maxCommonLen = Number(opt.maxCommonLen)|| 5,   // 選填，普通頁碼的最大個數
        
        className = opt.className  || "pagination",// 選填，分頁類型：pagination或pager等
        preText   = opt.preText    || "上一頁",      // 選填，上一頁文字顯示，適用顧只有前後頁按鈕的情怳
        nextText  = opt.nextText   || "下一頁",      // 選填，下一頁文字，同上
        firstText = opt.firstText  || "首頁",
	lastText  = opt.lastText   || "末頁",
        
        hasFirstBtn  = opt.hasFirstBtn   === false ? false : true,
        hasLastBtn   = opt.hasLastBtn    === false ? false : true,
        hasPreBtn    = opt.hasPreBtn     === false ? false : true,
        hasNextBtn   = opt.hasNextBtn    === false ? false : true,
        hasInput     = opt.hasInput      === false ? false : true,
        hasCommonPage= opt.hasCommonPage === false ? false : true,//選填，是否存在普通頁
        
        beforeFun = opt.beforeFun || null,  //選填，頁碼跳轉前調用的函數，可通過返回false來阻止跳轉，可接收目標頁碼函數
        afterFun  = opt.afterFun  || null,  //選填，頁碼跳轉後調用的函數，可接收目標頁碼函數
        noPageFun = opt.noPageFun || null;  //選填，頁碼總數為0時調用的函數
        
    //當前顯示的最小頁碼，用於計算起始頁碼，直接容器,當前頁，前，後，首，末，輸入框
    var minNum=1,changeLen,$parent,$currentPage,$preBtn,$nextBtn,$firstBtn,$lastBtn,$input;
    
    //容器
    if (!$container || $container.length != 1){
        console.log("分頁容器不存在或不正确");
        return false;
    }
    //總頁數
    if(pageCount <= 0){
        if(noPageFun) noPageFun();
        return false;
    }
    //當前頁
    if (currentNum < 1) currentNum = 1;
    else if (currentNum > pageCount) currentNum = pageCount;
    //普通頁碼的最大個數，起始頁算法限制，不能小於3
    if(maxCommonLen<3) maxCommonLen=3;
    //跳轉頁響應長度，用於計算起始頁碼
    if(maxCommonLen>=8) changeLen=3;
    else if(maxCommonLen>=5) changeLen=2;
    else changeLen=1;
    
    $container.hide();
    _initPageNav();
    $container.show();
    
    function _initPageNav(){
        var initStr = [];
        initStr.push('<nav><ul class="'+ className +'" onselectstart="return false">');
        if(hasFirstBtn)initStr.push('<li class="first-page" value="1"><span>'+ firstText +'</span></li>');
        if(hasPreBtn)  initStr.push('<li class="pre-page"  value="' + (currentNum - 1) + '"><span>'+ preText +'</span></li>');
        if(hasNextBtn) initStr.push('<li class="next-page" value="' + (currentNum + 1) + '"><span>'+ nextText +'</span></li>');
        if(hasLastBtn) initStr.push('<li class="last-page" value="' + pageCount + '"><span>'+ lastText +'</span></li>');
        if(hasInput)   
            initStr.push('<div class="input-page-div">當前第<input type="text" maxlength="6" value="' + currentNum + '" />頁，共<span>'
                + pageCount
                + '</span>頁<button type="button" class="btn btn-xs input-btn-xs">確定</button></div>');
        initStr.push('</ul></nav>');
        
        $container.html(initStr.join(""));
        //初始化變量
        $parent=$container.children().children();
        if(hasFirstBtn) $firstBtn = $parent.children("li.first-page");
        if(hasPreBtn)   $preBtn   = $parent.children("li.pre-page");
        if(hasNextBtn)  $nextBtn  = $parent.children("li.next-page");
        if(hasLastBtn)  $lastBtn  = $parent.children("li.last-page");
        if(hasInput){
            $input  = $parent.find("div.input-page-div>input");
            $parent.find("div.input-page-div>button").click(function(){
                _gotoPage($input.val());
            });
        }    
        //初始化功能按鈕
        _buttonToggle(currentNum);
        //生成普通頁碼
        if(hasCommonPage) {
            _createCommonPage(_computeStartNum(currentNum), currentNum);
        }
        //綁定點擊事件
        $parent.on("click", "li",function () {
            var $this=$(this);
            if ($this.is("li") && $this.attr("value")){
                if(!$this.hasClass("disabled") && !$this.hasClass("active")){
                    _gotoPage($this.attr("value"));
                }
            }
        });
    }
    //跳轉到頁碼
    function _gotoPage(targetNum) {
        targetNum=_formatNum(targetNum);
        if (targetNum == 0 || targetNum == currentNum) return false;
        // 跳轉前回調函數
        if (beforeFun && beforeFun(targetNum) === false) return false;
        //修改值
        currentNum=targetNum;
        if(hasInput)   $input.val(targetNum);
        if(hasPreBtn)  $preBtn.attr("value", targetNum - 1);
        if(hasNextBtn) $nextBtn.attr("value", targetNum + 1);
        //修改功能按鈕的狀態
        _buttonToggle(targetNum);
        // 計算起始頁碼
        if(hasCommonPage) {
            var starNum = _computeStartNum(targetNum);
            if (starNum == minNum) {// 要顯示的頁碼是相同的
                $currentPage.removeClass("active");
                $currentPage = $parent.children("li.commonPage").eq(targetNum - minNum).addClass("active");
            } 
            else {// 需要刷新頁碼
                _createCommonPage(starNum, targetNum);
            }
        }
        // 跳轉後回調函數
        if (afterFun) afterFun(targetNum);
    }
    //整理目標頁碼的值
    function _formatNum(num){
        num = Number(num);
        if(isNaN(num)) num=0;
        else if (num <= 0) num = 1;
        else if (num > pageCount) num = pageCount;
        return num;
    }
    //功能按鈕的開啟與關閉
    function _buttonToggle(current){
        if (current == 1) {
            if(hasFirstBtn) $firstBtn.addClass("disabled");
            if(hasPreBtn)   $preBtn.addClass("disabled");
        } 
        else {
            if(hasFirstBtn) $firstBtn.removeClass("disabled");
            if(hasPreBtn)   $preBtn.removeClass("disabled");
        }
        
        if (current == pageCount) {
            if(hasNextBtn) $nextBtn.addClass("disabled");
            if(hasLastBtn) $lastBtn.addClass("disabled");
        }
        else {
            if(hasNextBtn) $nextBtn.removeClass("disabled");
            if(hasLastBtn) $lastBtn.removeClass("disabled");
        }
    }
    //計算當前顯示的起始頁碼
    function _computeStartNum(targetNum) {
        var startNum;
        if (pageCount <= maxCommonLen)
            startNum = 1;
        else {
            if ((targetNum - minNum) >= (maxCommonLen-changeLen)) {//跳轉到靠後的頁碼
                startNum = targetNum - changeLen;
                if ((startNum + maxCommonLen-1) > pageCount) startNum = pageCount - (maxCommonLen-1);// 邊界修正
            } 
            else if ((targetNum - minNum) <= (changeLen-1)) {//跳轉到靠前的頁碼
                startNum = targetNum - (maxCommonLen-changeLen-1);
                if (startNum <= 0) startNum = 1;// 邊界修正
            } 
            else {// 不用改變頁碼
                startNum = minNum;
            }
        }
        return startNum;
    }
    //生成普通頁碼
    function _createCommonPage(startNum, activeNum) {
        var initStr = [];
        for (var i = 1,pageNum=startNum; i <= pageCount && i <= maxCommonLen; i++ , pageNum++) {
            initStr.push('<li class="commonPage" value="' + pageNum + '"><a href="javascript:">' + pageNum + '</a></li>');
        }
        
        $parent.hide();
        $parent.children("li.commonPage").remove();
        if(hasPreBtn) $preBtn.after(initStr.join(""));
        else if(hasFirstBtn) $firstBtn.after(initStr.join(""));
        else $parent.prepend(initStr.join(""));
        minNum = startNum;
        $currentPage = $parent.children("li.commonPage").eq(activeNum-startNum).addClass("active");
        $parent.show();
    }
}

