package irille.pub.idu;

import java.util.List;

import irille.pub.bean.Bean;

public class IduInsLines<T extends IduInsLines, BEAN extends Bean, LINES extends Bean>
		extends IduIns<T, BEAN> {
	private List<LINES> _lines;

	public List<LINES> getLines() {
		return _lines;
	}

	public void setLines(List<LINES> lines) {
		_lines = lines;
	}

}
