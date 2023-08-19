package br.com.saart.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oneDrive", url = "${drive.url}")
public interface OneDriveCli {

    @GetMapping(path = "${drive.path-for-info}", headers = {"user-agent=insomnia/2023.1.0", "accept=*/*"})
    InfoDto downloadInfo();

    @GetMapping(path = "${drive.path-for-file}", headers = {"user-agent=insomnia/2023.1.0", "accept=*/*"})
    Response downloadFile(@RequestParam("resid") String resid, @RequestParam("authkey") String authkey);

}
