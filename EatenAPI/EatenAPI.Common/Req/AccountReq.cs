using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.Common.Req
{
    public class AccountReq
    {
        public int AccountId { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string DisplayName { get; set; }
        public string AvatarURL { get; set; }
        public string Gender { get; set; }
        public int? YearOfBirth { get; set; }
    }
}
