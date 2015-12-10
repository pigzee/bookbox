function trim(mystr){
		while ((mystr.indexOf(" ")==0) && (mystr.length>1)){
			mystr=mystr.substring(1,mystr.length);
			}//去除前面空格
		while ((mystr.lastIndexOf(" ")==mystr.length-1)&&(mystr.length>1)){
			mystr=mystr.substring(0,mystr.length-1);
			}//去除后面空格
		if (mystr==" "){
			mystr="";
		}
	return mystr;
}

function legicStr(str){
	reg=/[,\(\)\[\]\}\{\}]/;     
	if(!reg.test(str)){    
		return true;  
	}else{    
		return false; 
	}
}

function compareDay(a,b){//a , b 格式為 yyyy-MM-dd
				 //var a1 = a.split("-");
				 //var b1 = b.split("-");
				 var d1 = new Date(a.replace(/-/g, "/"));
				 var d2 = new Date(b.replace(/-/g, "/"));
    //alert(d1 + '---' + d2);
                 if(d1.getTime() == d2.getTime()) {
                     return 0;
                 } else if (d1.getTime() < d2.getTime()) {
                     return 1;
                 } else if (d1.getTime() > d2.getTime()) {
                     return -1;
                 }
				 //if(Date.parse(d1) - Date.parse(d2) == 0){ //a==b
					//return 0;
				 //}
				 //if (Date.parse(d1) - Date.parse(d2) < 0) {//a>b
					//return 1;
				 //}
				 //if (Date.parse(d1) - Date.parse(d2) > 0) {//a<b
					//return -1;
				 //}
			} 

//校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
            function isTel(s)
            {
            //国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"

             var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
             //var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
                 if(s!="")
                 {
                     if(!pattern.exec(s))
                     {
						 return false;
                     }else
						 return true;
                 }
            }


function isMobile(s)
            {
             var reg0=/^13\d{9}$/;
			var reg1=/^153\d{8}$/;
			var reg2=/^159\d{8}$/;
			var reg3=/^187\d{8}$/;
			var reg4=/^15\d{9}$/;
			 var my=false;
			 if (reg0.test(s))my=true;
			 if (reg1.test(s))my=true;
			 if (reg2.test(s))my=true;
			 if (reg3.test(s))my=true;
			 if (reg4.test(s))my=true;
			 if (!my){
				return false;
			 }else
				 return true;
            }


//判断日期格式YYYY-MM-DD
//限中文操作系统

function checkDate(inputDate) {
    if (inputDate != " ") {
        var reg = /^(\d{4})\-(\d{2})\-(\d{2})$/;

        if (reg.exec(inputDate) == null) {
            return false;
        }
//		var   year   =   RegExp.$1;
//		var   month   =   RegExp.$2;
//		var   day   =   RegExp.$3;
//		var   d   =   new   Date(month+ "/ "+day+ "/ "+year);
//		alert(year + '--' + month + '--' + day + '---' + d);
//		var   dateStr   =   d.toLocaleString();
//		newMonth   =   dateStr.substring(dateStr.indexOf( "年")+1,dateStr.indexOf( "月"));
//
//		if(newMonth!=   parseInt(month,10).toString())
//		{
//			return   false;
//		}else
//		{
//			return   true;
//		}
        return true;
    }
}