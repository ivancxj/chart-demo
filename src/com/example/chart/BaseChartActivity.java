/*package com.tianrunjs.trjsdss.context;

import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;

import com.tianrunjs.trjsdss.bean.TableData;
import com.tianrunjs.trjsdss.bean.TableData.Data;

public abstract class BaseChartActivity extends BaseTableActivity
{
	protected GraphicalView mView;
	
	@Override
	public void updateView(List<TableData> result)
	{
		if(result == null||this.result == result) return;
		this.result = result;
		mView = new GraphicalView(this, getChart());
		setContentView(mView);
	}
	
	protected abstract AbstractChart getChart();
	
	
	public double maxValue(double[] value1,double[] value2)
	{
		double maxvalue=0;
		int mvalue=0;
		
		for(int i=0;i<value1.length;i++){
			if(maxvalue<value1[i])
				maxvalue=value1[i];
		}
		
		for(int j=0;j<value2.length;j++){
			if(maxvalue<value2[j])
				maxvalue=value2[j];			
		}
		
		maxvalue=maxvalue/0.7;
		if(maxvalue<100){
			mvalue=(int)maxvalue/10+1;
			maxvalue=mvalue*10;
		}
		else if(maxvalue<1000){
			mvalue=(int)maxvalue/100+1;
			maxvalue=mvalue*100;
		}
		else{
			mvalue=(int)maxvalue/1000+1;
			maxvalue=mvalue*1000;
		}
			
		return maxvalue;
	}

}
*/