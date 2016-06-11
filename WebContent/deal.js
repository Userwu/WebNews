var rowRandom = 8;
var row = 5;
function load() {
	var url = encodeURI("essayListAction?type1=动漫&page=1");
	$("#essayList").load(url);
}
function getListByTypeAndPage(type1, page) {

	var url = encodeURI("essayListAction!getList?type1=" + type1 + "&page="
			+ page);
	$("#essayList").load(url);
}
function getListByKeyAndPage(key, page) {
	var url = encodeURI("essayListAction!searchList?key=" + key + "&page=" + page);
	$("#searchList").load(url);
}
function getListByPage(page){
	 window.location.href="search.jsp?key="+document.getElementById("key").value;
	//var url = encodeURI("essayListAction!searchList?key=" + document.getElementById("key").value + "&page=" + page);
	//$("#searchList").load(url);
}
function getEssayByAc(ac) {
	var url = encodeURI("essayAction?ac=" + ac);
	$("#essayReader").load(url);
}
function getEssayListRandom(type2, row, need) {
	var url = encodeURI("essayListAction!getRandom?type2=" + type2 + "&row="
			+ row + "&need=" + need);
	if (need == "RANDOMLIST") {
		$("#essayListRandom1").load(url);
		$("#essayListRandom2").load(url);
	} else {
		$("#essayListRandom").load(url);
	}

}
function entersearch(){
    //alert(dd);
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13)
   {
	   getListByPage(1);
   }
}
function getEssayList(type1, page) {
	changeBaner(type1);
	getListByTypeAndPage(type1, page);
	getEssayListRandom(type1, row, "LUNBOTU");
	getEssayListRandom(type1, rowRandom, "RANDOMLIST");
}
function nofind() {
	var img = event.srcElement;
	img.src = "img/default.png";
	img.onerror = null;
}
function changeBaner(type) {
	for (var i = 1; i <= 5; i++) {
		$("li#" + i).removeClass("banner-choose");
		$("li#" + i).children().removeClass("achoose");
		
	}
	var str = [
		"综合", "工作", "动漫", "漫画", "游戏"
		]
	;
	for (var i = 1; i <= 5; i++) {
		if (type == str[i - 1]) {
			$("li#" + i).addClass("banner-choose");
			$("li#" + i).children().addClass("achoose");
			break;
		}
	}
}