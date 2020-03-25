package com.forb.forbapp.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.forb.forbapp.domain.LiveOrder;
import com.forb.forbapp.inputtypes.SingleOrderIto;

/**
 * Main service for the app
 * 
 * @author Vikram Aroskar
 *
 */
@Service
public class ForbAppService {
	/*
	 * @Autowired LiveOrderMaster liveOrderMaster;
	 */

	@Resource(name = "liveordermaster")
	List<LiveOrder> listOrders;

	public Object placeOrder(@Valid SingleOrderIto so) {

		LiveOrder lo = new LiveOrder(so.getOrder_type(), so.getOrder_price(), so.getOrder_quantity());

		// System.out.println("size" + listOrders.size());

		int qtyRemaining = lo.getOrder_quantity();

		if ("BUY".equalsIgnoreCase(lo.getOrder_type())) {

			List<LiveOrder> selllist = listOrders.stream()
					.filter(predicate -> predicate.getOrder_type().equalsIgnoreCase("SELL"))
					.filter(predicate -> predicate.getOrder_price() < lo.getOrder_price()).collect(Collectors.toList());
			selllist.sort(new TimeSorter());
			int sellersCount = 0;
			while (qtyRemaining > 0 && selllist.size() > 0) {
				
				LiveOrder o = selllist.get(sellersCount);
				sellersCount++;
				int sellqty = o.getOrder_quantity() - qtyRemaining;
				qtyRemaining = qtyRemaining - o.getOrder_quantity();
				if (sellqty < 0) {
					listOrders.remove(listOrders.stream().filter(predicate -> predicate.getId() == o.getId()));
					selllist.remove(listOrders.stream().filter(predicate -> predicate.getId() == o.getId()));
					sellersCount--;
				} else {
					((LiveOrder) (listOrders.stream().filter(predicate -> predicate.getId() == o.getId()).findFirst())
							.get()).setOrder_quantity(sellqty);
				}

			}

		} else if ("SELL".equalsIgnoreCase(lo.getOrder_type())) {
			List<LiveOrder> buylist = listOrders.stream()
					.filter(predicate -> predicate.getOrder_type().equalsIgnoreCase("BUY"))
					.filter(predicate -> predicate.getOrder_price() > lo.getOrder_price()).collect(Collectors.toList());
			buylist.sort(new TimeSorter());

			int buyerCount = 0;
			while (qtyRemaining > 0 && buylist.size() > 0) {
				LiveOrder o = buylist.get(buyerCount);
				buyerCount++;
				int buyqty = o.getOrder_quantity() - qtyRemaining;
				qtyRemaining = qtyRemaining - o.getOrder_quantity();
				if (buyqty < 0) {
					listOrders.remove(listOrders.stream().filter(predicate -> predicate.getId() == o.getId()));
					buylist.remove(listOrders.stream().filter(predicate -> predicate.getId() == o.getId()));
					buyerCount--;
				} else {
					((LiveOrder) (listOrders.stream().filter(predicate -> predicate.getId() == o.getId()).findFirst())
							.get()).setOrder_quantity(buyqty);
				}

			}
		}

		if (qtyRemaining > 0) {
			lo.setOrder_quantity(qtyRemaining);
			listOrders.add(lo);
		}

		return listOrders;
	}

}

class TimeSorter implements Comparator<LiveOrder> {
	@Override
	public int compare(LiveOrder o1, LiveOrder o2) {
		return o2.getCreateDateTime().compareTo(o1.getCreateDateTime());
	}
}
