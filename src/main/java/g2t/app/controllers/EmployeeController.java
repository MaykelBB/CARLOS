package g2t.app.controllers;

import g2t.app.domain.Employee;
import g2t.app.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @RequestMapping("/trabajador")
    public String deviceList(Model model){
        model.addAttribute("pageTitle", "GPS2Track - Listado Trabajadores");
        model.addAttribute("employees", employeeService.getActiveEmployees());
        return "views/employees/list";
    }

    @RequestMapping("/trabajador/{rut}")
    public String showDevice(@PathVariable long rut, Model model){
        //logger.info(deviceService.getDevice(imei).toString());
        model.addAttribute("pageTitle", "GPS2Track - Trabajador");
        model.addAttribute("employee", employeeService.getEmployee(rut));
        return "views/employees/show";
    }

    @RequestMapping("/trabajador/nuevo")
    public String newDevice(Model model){
        model.addAttribute("pageTitle", "GPS2Track - Nuevo Trabajador");
        model.addAttribute("employee", new Employee());
        return "views/employees/new";
    }

    @RequestMapping(value = "/trabajador/save", method = RequestMethod.POST)
    public String saveDevice(Employee employee){
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return "redirect:/trabajador/" + savedEmployee.getRut();
    }
}