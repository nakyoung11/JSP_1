<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=72c0231a0bfe0d103941d477d149d70e"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<div id="mapContainer" style="width:100%; height:100%;"></div>
	<script type="text/javascript">
		const options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(35.8656711, 128.5939705), //지도의 중심좌표.
			level: 4	//지도의 레벨(확대, 축소 정도)
		};
		const map = new kakao.maps.Map(mapContainer, options); //지도 생성 및 객체 리턴
		
		function getRestaurantList(){
			axios.get('/restaurant/ajaxGetList').then(function(rse){
			res.data.forEach(function(item){
				const na={
						'na':{
						'Ga': item.lng,
						'ha': item.lat
						}
				}
			} 
			var marker.
				
			})	
				
			})
		}
		getRestaurantList()
		
		</script>	
</div>
