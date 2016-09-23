package irille.wpt.extjs;

import java.util.List;

public class ComboTriggerBuilder {

	private List<IComboTrigger> data;
	
	public ComboTriggerBuilder(List<IComboTrigger> data) {
		this.data = data;
	}
	public ComboTrigger build() {
		ComboTrigger comboTrigger = new ComboTrigger();
		for(IComboTrigger line:data) {
			ComboTriggerItem item = new ComboTriggerItem(line.getText(), line.getValue());
			comboTrigger.add(item);
		}
		return comboTrigger;
	}
}
