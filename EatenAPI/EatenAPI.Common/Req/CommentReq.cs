using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.Common.Req
{
    public class CommentReq
    {
        public int CommentId { get; set; }
        public int PostId { get; set; }
        public int AccountId { get; set; }
        public string Content { get; set; }
        public int? React { get; set; }
        public int? Rate { get; set; }
    }
}
