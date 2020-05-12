using Eaten.Common.BLL;
using Eaten.Common.Req;
using Eaten.Common.Rsp;
using Eaten.DAL;
using Eaten.DAL.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Eaten.BLL
{
    public class TheLoaiSvc : GenericSvc<TheLoaisRep, TheLoai>
    {
        public override SingleRsp Read(int id)
        {
            var res = new SingleRsp();

            var m = _rep.Read(id);
            res.Data = m;

            return res;
        }

        public override SingleRsp Update(TheLoai m)
        {
            var res = new SingleRsp();
            var m1 = m.MaLoai > 0 ? _rep.Read(m.MaLoai) : _rep.Read(m.TenLoai);
            if (m1 == null)
                res.SetError("EZ103", "No Data.");
            else
            {
                res = base.Update(m);
                res.Data = m;
            }
            return res;
        }

        public SingleRsp CreateTheLoai(TheLoaiReq theLoai)
        {
            var res = new SingleRsp();
            var tl = new TheLoai()
            {
                MaLoai = theLoai.MaLoai,
                TenLoai = theLoai.TenLoai
            };
            res = _rep.CreateTheLoai(tl);
            return res;
        }

        public SingleRsp UpdateTheLoai(TheLoaiReq theLoai)
        {
            var res = new SingleRsp();
            var tl = new TheLoai()
            {
                MaLoai = theLoai.MaLoai,
                TenLoai = theLoai.TenLoai
            };
            res = _rep.UpdateTheLoai(tl);
            return res;
        }

        public SingleRsp DeleteTheLoai(int maLoai)
        {
            var res = new SingleRsp();
            var tl = _rep.All.First(p => p.MaLoai == maLoai);
            res = _rep.DeleteTheLoai(tl);
            return res;
        }
    }
}
