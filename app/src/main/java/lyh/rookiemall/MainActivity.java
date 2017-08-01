package lyh.rookiemall;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lyh.rookiemall.bean.Tab;
import lyh.rookiemall.fragment.CartFragment;
import lyh.rookiemall.fragment.CategoryFragment;
import lyh.rookiemall.fragment.HomeFragment;
import lyh.rookiemall.fragment.HotFragment;
import lyh.rookiemall.fragment.MineFragment;


public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs=new ArrayList<>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();
    }

    private void initTab() {
        Tab tab_home=new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab tab_hot=new Tab(R.string.hot,R.drawable.selector_icon_hot,HotFragment.class);
        Tab tab_category=new Tab(R.string.catagory,R.drawable.selector_icon_category, CategoryFragment.class);
        Tab tab_cart=new Tab(R.string.cart,R.drawable.selector_icon_cart,CartFragment.class);
        Tab tab_mine=new Tab(R.string.mine,R.drawable.selector_icon_mine,MineFragment.class);
        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);
        mInflater=LayoutInflater.from(this);
        mTabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        for (Tab tab:mTabs){
            TabHost.TabSpec tabSpec=mTabHost.newTabSpec(getString(tab.getTitle()));
            View  view=buildindicator(tab);
            tabSpec.setIndicator(view);
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);

    }
    private View buildindicator(Tab tab){
        View view=mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img= (ImageView) view.findViewById(R.id.icon_tab);
        TextView text= (TextView)view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
