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
    using EatenAPI.DAL.ViewModels;
    using System.Linq;
    public class CommentsSvc : GenericSvc<CommentsRep, Comments>
    {
        public override SingleRsp Read(int id)
        {
            var res = new SingleRsp();

            var m = _rep.Read(id);
            res.Data = m;

            return res;
        }

        public override SingleRsp Update(Comments m)
        {
            var res = new SingleRsp();
            var m1 = m.CommentId > 0 ? _rep.Read(m.CommentId) : _rep.Read(m.Content);
            if (m1 == null)
                res.SetError("EZ103", "No Data.");
            else
            {
                res = base.Update(m);
                res.Data = m;
            }
            return res;
        }

        public SingleRsp CreateComment(CommentReq cmt)
        {
            var res = new SingleRsp();
            var cmtNew = new Comments()
            {
                PostId = cmt.PostId,
                AccountId = cmt.AccountId,
                Content = cmt.Content,
                React = cmt.React,
                Rate = cmt.Rate
            };
            res = _rep.CreateComment(cmtNew);
            return res;
        }

        public SingleRsp UpdateComment(CommentReq cmt)
        {
            var res = new SingleRsp();
            var cmtUpdate = new Comments()
            {
                CommentId = cmt.CommentId,
                PostId = cmt.PostId,
                AccountId = cmt.AccountId,
                Content = cmt.Content,
                React = cmt.React,
                Rate = cmt.Rate
            };
            res = _rep.UpdateComment(cmtUpdate);
            return res;
        }

        public SingleRsp DeleteComment(int id)
        {
            var res = new SingleRsp();
            var cmt = _rep.All.First(p => p.CommentId == id);
            res = _rep.DeleteComment(cmt);
            return res;
        }

        public IEnumerable<CommentViewModel> GetAllCommentInfo()
        {
            var res = _rep.GetAllCommentInfo();
            return res;
        }
    }
}
