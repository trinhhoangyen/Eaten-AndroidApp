using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EatenAPI.BLL;
using EatenAPI.Common.Req;
using EatenAPI.Common.Rsp;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EatenAPI.WEB.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountsController : ControllerBase
    {
        public AccountsController()
        {
            _svc = new AccountsSvc();
        }

        [HttpPost("get-by-id")]
        public IActionResult getAccountById([FromBody]SimpleReq req)
        {
            var res = new SingleRsp();
            res = _svc.Read(req.Id);
            return Ok(res);
        }

        [HttpPost("get-info-account-by-id")]
        public IActionResult GetInfoAccountByID([FromBody]InfoAccountReq req)
        {
            var res = _svc.GetInfoAccountByID(req.AccountId);
            return Ok(res);
        }

        [HttpPost("get-account-login")]
        public IActionResult getAccountLogin([FromBody]AccountReq req)
        {
            var res = new SingleRsp();
            res = _svc.Login(req.Email, req.Password);
            return Ok(res);
        }

        [HttpPost("get-all")]
        public IActionResult getAllAccount()
        {
            var res = new SingleRsp();
            res.Data = _svc.All;
            return Ok(res);
        }

        [HttpPost("create-account")]
        public IActionResult CreateAccount([FromBody]AccountReq req)
        {
            var res = _svc.CreateAccount(req);
            return Ok(res);
        }

        [HttpPost("update-account")]
        public IActionResult UpdateAccount([FromBody]AccountReq req)
        {
            var res = _svc.UpdateAccount(req);
            return Ok(res);
        }

        [HttpPost("delete-account")]
        public IActionResult DeleteAccount([FromBody]AccountReq req)
        {
            var res = _svc.DeleteAccount(req.AccountId);
            return Ok(res);
        }

        private readonly AccountsSvc _svc;
    }
}