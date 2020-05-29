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
    public class PicturesSvc : GenericSvc<PicturesRep, Pictures>
    {
        #region Override
        public override SingleRsp Read(int id)
        {
            var res = new SingleRsp();

            var m = _rep.Read(id);
            res.Data = m;

            return res;
        }
        public override SingleRsp Update(Pictures m)
        {
            var res = new SingleRsp();
            var m1 = m.PictureId > 0 ? _rep.Read(m.PictureId) : _rep.Read((int)m.PostId);
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
        public SingleRsp CreatePicture(PictureReq pic)
        {
            var res = new SingleRsp();
            var picNew = new Pictures()
            {
                PostId = pic.PostId,
                PictureURL = pic.PictureURL
            };
            res = _rep.CreatePicture(picNew);
            return res;
        } 
        public SingleRsp UpdatePicture(PictureReq pic)
        {
            var res = new SingleRsp();
            var picUpdate = new Pictures()
            {
                PictureId = pic.PictureId,
                PostId = pic.PostId,
                PictureURL = pic.PictureURL
            };
            res = _rep.UpdatePicture(picUpdate);
            return res;
        }
        public SingleRsp DeletePicture(int id)
        {
            var res = new SingleRsp();
            var acc = _rep.All.First(p => p.PictureId == id);
            res = _rep.DeletePicture(acc);
            return res;
        }
        #endregion
    }
}
