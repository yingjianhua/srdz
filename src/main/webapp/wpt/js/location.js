function Location(longitude, latitude) {
	this.longitude = longitude;
	this.latitude = latitude;
}
Location.prototype.R = 6371;
Location.prototype.rad = function(len) {
	return len*Math.PI/180;
}
Location.prototype.getDistance = function(dest) {
	if(dest instanceof Location) {
		var radLat1 = this.rad(this.latitude);  
	    var radLat2 = this.rad(dest.latitude);  
	    var a = radLat1 - radLat2;  
	    var b = this.rad(this.longitude) - this.rad(dest.longitude);  
	    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
	    s = s * this.R;  
	    return s;  
	}
}