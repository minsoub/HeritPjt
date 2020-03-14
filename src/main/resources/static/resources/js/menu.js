/*top_menu 관련 js*/
function load_fnc(depth_1, depth_2, depth_3){
	menu_load(depth_1,depth_2,depth_3);
	topmenu_load(depth_1,depth_2,depth_3);
}

function topmenu_load(depth_1,depth_2,depth_3){
		$("#topmenu-framebox").removeClass('mob-topmenu');
		$("#topmenu-framebox").addClass('web-topmenu');
		if($("#wrapper").find('#m-menubox').length){
			$("#topmenu-framebox").unwrap().unwrap();
		}

		//web-topmenu
		$(".web-topmenu>ul>li").on({
			mouseenter:function(){
				console.log("on called...");
				var hover_menu = $(this).attr('id').replace('topmenu','');
				console.log("hover_menu : " + hover_menu);
				for(var t=1;t<=$(".web-topmenu>ul>li").length;t++){
					if(t == hover_menu){
						$("#topmenu"+hover_menu+">a").addClass('active');
					}else{
						$("#topmenu"+t+">a").removeClass('active');
					}
				}
			},mouseleave:function(){
				console.log("leaved..");
			}
		});
			
		$("#header").on('mouseleave',function(){menu_load(depth_1,depth_2,depth_3);});
}

function menu_load(depth_1,depth_2,depth_3){

	if(depth_1){
		$("li[id^=topmenu]>a").removeClass('active');

		$("#topmenu"+ depth_1+">a").addClass('active');
		//if(depth_2){$("#topmenu"+ depth_1).children().eq(0).children().eq(depth_2 - 1).addClass('menu-on');}
		//if(depth_3){$("#topmenu"+ depth_1).children().eq(0).children().eq(depth_2 - 1).children().eq(0).children().eq(depth_3 - 1).addClass('menu-on');}
	}		
}