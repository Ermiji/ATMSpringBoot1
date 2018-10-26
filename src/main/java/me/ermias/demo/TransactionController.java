package me.ermias.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/")
    public String home(Model model){

        model.addAttribute(new Transaction());
        return "home";
    }

    @RequestMapping("/withdrawalForm")
    public String withdrawal(Model model){

        model.addAttribute(new Transaction());
        return "withdrawalForm";
    }
    @RequestMapping(path = "/withdraw", method= RequestMethod.POST)
    public String withdraw(@Valid Transaction tn , BindingResult bindingResult, long actNo,
                           Double amount, Model model){

        if(bindingResult.hasErrors()){
            return "withdrawalForm";
        }
        Transaction tr = new Transaction();
        tr.setActNo(actNo);
        tr.setWithdrawal(true);
        tr.setAction("withdrawal");
        tr.setAmount(0-amount);
        tr.setReason("LargePurchase");
        model.addAttribute("transaction", tr);
        transactionRepository.save(tr);

        return "balance";
    }

    @RequestMapping(path="/deposit")
    public String deposit(Model model){

        model.addAttribute(new Transaction());
        return "depositForm";
    }
    @RequestMapping(path="/deposit", method=RequestMethod.POST)
    public String deposit(@Valid Transaction tn ,  BindingResult bindingResult,long actNo,
                          double amount, Model model){

        if(bindingResult.hasErrors()){
            return "depositForm";
        }
        Transaction tr1 = new Transaction();
        tr1.setActNo(actNo);
        tr1.setDeposit(true);
        tr1.setAction("deposit");
        tr1.setAmount(amount);
        model.addAttribute("transaction", tr1);
        transactionRepository.save(tr1);

        return "balanceForm";

    }

    @RequestMapping("/account")
    public String account(@Valid Transaction tn, BindingResult bindingResult, Model model){

        model.addAttribute(new Transaction());
        return "accountForm";
    }

    @RequestMapping(path = "/balance", method=RequestMethod.POST)
    public String balance(long actNo, Model model){

        List<Transaction> trs = getTransWithAccNo(actNo);
        if(trs == null || trs.size() == 0){
            Transaction tr2 = new Transaction();
            tr2.setActNo(actNo);
            trs.add(tr2);
            model.addAttribute("transactions", trs);
            List<Double> balance = calculateBalance(trs);
            model.addAttribute("balance", balance);
            return "transactionHistory";

        }else{
            List<Double> balance = calculateBalance(trs);
            model.addAttribute("transactions", trs);
            model.addAttribute("balance", balance);
            return "transactionHistory";
        }

    }

    public List<Transaction> getTransWithAccNo(long actNo){

        List<Transaction> t = transactionRepository.findByActNo(actNo);

        return t;

    }

    public List<Double> calculateBalance(List<Transaction> trans){

        double balance = 0;
        List<Double> balanceList = new ArrayList<Double>();
        for(Transaction tra: trans){
            balance = balance + tra.getAmount();
            balanceList.add(balance);
        }
        return balanceList;

    }


}
