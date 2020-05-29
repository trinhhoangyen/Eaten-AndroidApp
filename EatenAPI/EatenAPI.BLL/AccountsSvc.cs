using EatenAPI.Common.BLL;
using EatenAPI.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.BLL
{
    using Common.Req;
    using Common.Rsp;
    using System.Linq;
    public class AccountsSvc : GenericSvc<AccountsRep, Accounts>
    {
        #region Override
        public override SingleRsp Read(int id)
        {
            var res = new SingleRsp();

            var m = _rep.Read(id);
            res.Data = m;

            return res;
        }
        public override SingleRsp Update(Accounts m)
        {
            var res = new SingleRsp();
            var m1 = m.AccountId > 0 ? _rep.Read(m.AccountId) : _rep.Read(m.DisplayName);
            if (m1 == null)
                res.SetError("EZ103", "No Data.");
            else
            {
                res = base.Update(m);
                res.Data = m;
            }
            return res;
        }
        #endregion

        #region Methods
        public InfoAccountReq GetInfoAccountByID(int id)
        {
            var res = _rep.GetInfoAccountByID(id);
            return res;
        }
        public SingleRsp Login(string email, string password)
        {
            var res = new SingleRsp();

            var m = _rep.Login(email, password);
            res.Data = m;

            return res;
        }
        public SingleRsp CreateAccount(AccountReq acc)
        {
            var res = new SingleRsp();
            var accNew = new Accounts()
            {
                Email = acc.Email,
                Password = acc.Password,
                DisplayName = acc.DisplayName,
                AvatarURL = acc.AvatarURL,
                Gender = acc.Gender,
                YearOfBirth = acc.YearOfBirth
            };
            res = _rep.CreateAccount(accNew);
            return res;
        }
        public SingleRsp UpdateAccount(AccountReq acc)
        {
            var res = new SingleRsp();
            var accUpdate = new Accounts()
            {
                AccountId = acc.AccountId,
                Email = acc.Email,
                Password = acc.Password,
                DisplayName = acc.DisplayName,
                AvatarURL = acc.AvatarURL,
                Gender = acc.Gender,
                YearOfBirth = acc.YearOfBirth
            };
            res = _rep.UpdateAccount(accUpdate);
            return res;
        }
        public SingleRsp DeleteAccount(int id)
        {
            var res = new SingleRsp();
            var acc = _rep.All.First(p => p.AccountId == id);
            res = _rep.DeleteAccount(acc);
            return res;
        }
        #endregion
    }
}
