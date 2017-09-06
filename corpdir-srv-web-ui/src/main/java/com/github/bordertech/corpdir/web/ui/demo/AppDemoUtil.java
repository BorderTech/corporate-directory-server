package com.github.bordertech.corpdir.web.ui.demo;

import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.web.ui.model.OrgUnitModel;
import com.github.bordertech.corpdir.web.ui.panel.BasicEntityPanel;
import com.github.bordertech.corpdir.web.ui.view.PositionTypeCrudView;
import com.github.bordertech.corpdir.web.ui.view.UnitTypeCrudView;
import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.Margin;
import com.github.bordertech.wcomponents.Size;
import com.github.bordertech.wcomponents.WApplication;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.lib.WDiv;
import com.github.bordertech.wcomponents.lib.app.CriteriaTextView;
import com.github.bordertech.wcomponents.lib.app.DefaultFormView;
import com.github.bordertech.wcomponents.lib.app.ListBasicView;
import com.github.bordertech.wcomponents.lib.app.SelectMenuView;
import com.github.bordertech.wcomponents.lib.app.combo.DefaultCrudView;
import com.github.bordertech.wcomponents.lib.app.combo.DefaultCrudView2;
import com.github.bordertech.wcomponents.lib.app.combo.FormWithSelectView;
import com.github.bordertech.wcomponents.lib.app.combo.FormWithToolbarView;
import com.github.bordertech.wcomponents.lib.app.combo.ListWithCriteriaTextView;
import com.github.bordertech.wcomponents.lib.app.combo.SelectWithCriteriaTextView;
import com.github.bordertech.wcomponents.lib.app.view.CriteriaView;
import com.github.bordertech.wcomponents.lib.app.view.FormView;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;
import com.github.bordertech.wcomponents.lib.demo.model.MyStringSearchModel;
import com.github.bordertech.wcomponents.lib.mvc.ComboView;
import com.github.bordertech.wcomponents.lib.mvc.View;
import com.github.bordertech.wcomponents.lib.mvc.msg.MsgEventType;

/**
 *
 * @author jonathan
 */
public class AppDemoUtil {

	private AppDemoUtil() {
	}

	public static void configApplication(final WApplication app) {
	}

	public static WDiv buildDemo() {
//		WView view1 = buildView1(dispatcher);
//		WView view2 = buildView2(dispatcher);
//		WView view3 = buildView3(dispatcher);
//		WView view4 = buildView4(dispatcher);
		final ComboView view5 = buildView5();
//		final ComboView crud = buildCrudView();
		final View crud2 = buildCrudView2();

		//-----------
		// MAIN Controller
//		ctrl.addView(view1);
//		ctrl.addView(view2);
//		ctrl.addView(view3);
//		ctrl.addView(view4);
//		view5.configViews();
//		crud.configViews();
		WButton button = new WButton("Reset");
		button.setAction(new Action() {
			@Override
			public void execute(ActionEvent event) {
				view5.reset();
//				crud.reset();
				crud2.reset();
			}
		});

		WDiv div = new WDiv();
		div.add(button);
//		div.add(wrapInSection(view1, "View 1"));
//		div.add(wrapInSection(view2, "View 2"));
//		div.add(wrapInSection(view3, "View 3"));
//		div.add(wrapInSection(view4, "View 4"));
//		div.add(wrapInSection(view5, "View 5"));
//		div.add(crud2);
		div.add(new PositionTypeCrudView("A"));
		div.add(new PositionTypeCrudView("B"));
		div.add(new UnitTypeCrudView("A3"));
		div.add(new UnitTypeCrudView("B3"));

		return div;
	}

	public static ComboView buildView1() {
		ListWithCriteriaTextView<String> view = new ListWithCriteriaTextView<>(new ListBasicView());
		// Set Model
		view.addModel("search", new MyStringSearchModel());
		return view;
	}

	public static ComboView buildView2() {
		FormView<OrgUnit> entView = new DefaultFormView<>();
		FormWithToolbarView<OrgUnit> view2 = new FormWithToolbarView<>(entView);
		entView.getFormHolder().add(new BasicEntityPanel());
		// Set Model
		view2.addModel("action", new OrgUnitModel());
		return view2;
	}

	public static ComboView buildView3() {
		ListWithCriteriaTextView<OrgUnit> view3 = new ListWithCriteriaTextView<>(new SelectMenuView());
		// Set Model
		view3.addModel("search", new OrgUnitModel());
		return view3;
	}

//	public static ViewCombo buildView4(final Dispatcher dispatcher) {
//		DefaultComboView view4 = new DefaultComboView(dispatcher, "Y");
//		// Views
//		CriteriaView<String> critView4 = new CriteriaTextView(dispatcher, "Y");
//		ListView<OrgUnit> listView4 = new SelectMenuView(dispatcher, "Y");
//		DefaultPollingView<String, List<OrgUnit>> pollingView4 = new DefaultPollingView<>(dispatcher, "Y");
//
//		// Set views on Controller
//		ListWithCriteriaCtrl<String, OrgUnit> ctrl4 = new ListWithCriteriaCtrl<>(dispatcher, "Y");
//		ctrl4.setCriteriaView(critView4);
//		ctrl4.setPollingView(pollingView4);
//		ctrl4.setListView(listView4);
//		// Set Model
//		ctrl4.addModel(new MyOrgUnitSearchModel());
//
//		// Add views to holder
//		WContainer holder = view4.getContent();
//		holder.add(ctrl4);
//		holder.add(critView4);
//		holder.add(pollingView4);
//		holder.add(listView4);
//		return view4;
//	}
	public static ComboView buildView5() {
		// Form View
		FormWithToolbarView<OrgUnit> entView = new FormWithToolbarView<>("X");
		entView.getFormView().getFormHolder().add(new BasicEntityPanel());
		// Select View
		SelectWithCriteriaTextView<OrgUnit> select = new SelectWithCriteriaTextView<>("X");
		// Form and Select View Together
		FormWithSelectView<String, OrgUnit> view = new FormWithSelectView<>(entView, select, "X");

		select.addDispatcherOverride("X1", MsgEventType.values());
		select.addListenerOverride("X1", MsgEventType.values());

		entView.addDispatcherOverride("X2", MsgEventType.values());
		entView.addListenerOverride("X2", MsgEventType.values());

		// Set Models
		view.addModel("action", new OrgUnitModel());
		view.addModel("search", new OrgUnitModel());
		view.setBlocking(true);
		return view;
	}

	public static View buildCrudView2() {

//		View view = new DefaultCrudView2("Org Unit", new MyOrgUnitActionModel(), new MyOrgUnitSearchModel(), new BasicEntityPanel(), "T");
		View view = new DefaultCrudView2("Org Unit", new OrgUnitModel(), new BasicEntityPanel(), "T");
		return view;
	}

	public static ComboView buildCrudView() {
		// Entity View
		FormView<OrgUnit> entity = new DefaultFormView<>();
		entity.getFormHolder().add(new BasicEntityPanel());
		// Select View
		SelectView<OrgUnit> select = new SelectMenuView<>();
		// Criteria View
		CriteriaView crit = new CriteriaTextView();

		DefaultCrudView view = new DefaultCrudView(crit, select, entity);

		// Set Models
		view.addModel("action", new MockOrgUnitActionModel());
		view.addModel("search", new OrgUnitModel());
		view.setBlocking(true);
		return view;
	}

	public static final WSection wrapInSection(final View view, final String title) {
		WSection section = new WSection(title);
		section.getContent().add(view);
		section.setMargin(new Margin(Size.XL));
		return section;
	}
}
