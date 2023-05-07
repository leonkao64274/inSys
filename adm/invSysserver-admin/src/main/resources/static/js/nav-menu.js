/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// begin
document.write('<ul class="nav" id="side-menu">');

// (0) sidebar search
document.write('    <li class="sidebar-search">');
document.write('        <div class="input-group custom-search-form">');
document.write('        <input type="text" class="form-control" placeholder="Search...">');
document.write('        <span class="input-group-btn">');
document.write('            <button class="btn btn-default" type="button">');
document.write('                <i class="fa fa-search"></i>');
document.write('            </button>');
document.write('        </span>');
document.write('        </div>');
document.write('    </li>');

// (1) 系統管理。
document.write('    <li>');
document.write('        <a href="#"><i class="fa fa-gear fa-fw"></i> 系統管理<span class="fa arrow"></span></a>');
document.write('        <ul class="nav nav-second-level">');
document.write('            <li>');
document.write('                <a href="../directory_server/query"><i class="fa fa-angle-right fa-fw"></i> 目錄服務器連線設定</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../directory_server_schedule/query"><i class="fa fa-angle-right fa-fw"></i> 卡段下載設定</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../invSys_trans_config/edit"><i class="fa fa-angle-right fa-fw"></i> 交易參數設定</a>');
document.write('            </li>');
document.write('        </ul');
document.write('    </li>');


// (3) 權限管理。
document.write('    <li>');
document.write('        <a href="#"><i class="fa fa-user fa-fw"></i> 權限管理<span class="fa arrow"></span></a>');
document.write('        <ul class="nav nav-second-level">');
document.write('            <li>');
document.write('                <a href="../admin_group/query"><i class="fa fa-angle-right fa-fw"></i> 群組管理</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../admin_group/op_query"><i class="fa fa-angle-right fa-fw"></i> 群組查詢</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../admin_user/query"><i class="fa fa-angle-right fa-fw"></i> 群組使用者管理</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../admin_user/op_query"><i class="fa fa-angle-right fa-fw"></i> 群組使用者查詢</a>');
document.write('            </li>');
document.write('        </ul');
document.write('    </li>');

//(4) 憑證管理。
document.write('    <li>');
document.write('        <a href="#"><i class="fa fa-user fa-fw"></i> 憑證管理<span class="fa arrow"></span></a>');
document.write('        <ul class="nav nav-second-level">');

document.write('            <li>');
document.write('                <a href="../ds_csr/query"><i class="fa fa-angle-right fa-fw"></i> DS 客戶端證書請求製作</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../ds_certificate/upload"><i class="fa fa-angle-right fa-fw"></i> DS 客戶端證書載入</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../ds_certificate/query"><i class="fa fa-angle-right fa-fw"></i> DS 客戶端證書查詢</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../ds_certificate/activate"><i class="fa fa-angle-right fa-fw"></i> DS 客戶端證書啟用</a>');
document.write('            </li>');

document.write('            <li>');
document.write('                <a href="../ca_certificate/load"><i class="fa fa-angle-right fa-fw"></i> 信任CA證書載入</a>');
document.write('            </li>');
document.write('            <li>');
document.write('                <a href="../ca_certificate/query"><i class="fa fa-angle-right fa-fw"></i> 信任CA證書查詢</a>');
document.write('            </li>');
document.write('        </ul');
document.write('    </li>');

//(4) 交易管理。
document.write('    <li>');
document.write('        <a href="#"><i class="fa fa-credit-card fa-fw"></i> 交易管理<span class="fa arrow"></span></a>');
document.write('        <ul class="nav nav-second-level">');
document.write('            <li>');
document.write('                <a href="../invSys_trans/query"><i class="fa fa-angle-right fa-fw"></i> 交易記錄查詢</a>');
document.write('            </li>');
document.write('        </ul');
document.write('    </li>');


// end
document.write('</ul>');