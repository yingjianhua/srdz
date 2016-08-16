package irille.tools;

public class LocationTools {

	/**
	 * 在mysql中创建根据两地经纬度计算距离的函数
	 * 
	 * 
CREATE FUNCTION getDistance(curLat DOUBLE, curLon DOUBLE, shopLat DOUBLE, shopLon DOUBLE)
RETURNS DOUBLE
BEGIN
	DECLARE dis DOUBLE;
	set dis = ACOS(SIN((curLat * 3.1415) / 180 ) * SIN((shopLat * 3.1415) / 180 ) + COS((curLat * 3.1415) / 180 ) * COS((shopLat * 3.1415) / 180 ) * COS((curLon * 3.1415) / 180 - (shopLon * 3.1415) / 180 ) ) * 6380 ;
	RETURN dis;
END;
	 */
}
