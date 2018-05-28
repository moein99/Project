<!--API key=AIzaSyA5CFOTho-uF4qjXK_CN8CpA57MzgZx0Jk-->
<!DOCTYPE html>
<html>

<body>

<div id="map" style="width:100%;height:600px;"></div>
<form id="mapform" action="SearchPage.jsp">
    <input type="text" name="t1" id="t1" style="display: none;">
    <input type="button" value="Search" onclick="validaty()">
</form>

<script>
    function validaty() {
        loc = document.getElementById("t1").value;
        if(loc == null ||loc == ""){
            alert("select location");
        }
        else{
            document.getElementById("mapform").submit();
        }
    }
    function myMap() {
        var mapCanvas = document.getElementById("map");
        var myCenter=new google.maps.LatLng(35.738593, 51.503258);
        var mapOptions = {center: myCenter, zoom: 15};
        var map = new google.maps.Map(mapCanvas, mapOptions);
        google.maps.event.addListener(map, 'click', function(event) {
            placeMarker(map, event.latLng);
        });
    }
    var marker;
    function placeMarker(map, location) {
        if (marker) {
            marker.setPosition(location);
        } else {
            marker = new google.maps.Marker({
                position: location,
                map: map,
                draggable: true
            });
        }
        document.getElementById("t1").value=location;
    }


</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA5CFOTho-uF4qjXK_CN8CpA57MzgZx0Jk&callback=myMap"></script>

</body>
</html>